package com.zgss.grib.gribdata.service;


import com.zgss.grib.common.util.DateUtil;
import com.zgss.grib.common.util.FileUtil;
import com.zgss.grib.entity.JsonQueueFile;
import com.zgss.grib.gribdata.util.WeatherFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class JsonMsgConsumer {
    private Logger logger = LoggerFactory.getLogger(JsonMsgConsumer.class);

    @Autowired
    Component_of_windService componentOfWindService;

    @Autowired
    Geopotential_heightService geopotentialHeightService;

    @Autowired
    Ice_water_mixing_ratioService iceWaterMixingRatioService;

    @Autowired
    Total_precipitationService totalPrecipitationService;

    @Autowired
    Relative_humidityService relativeHumidityService;

    @Autowired
    TemperatureService temperatureService;

    @Autowired
    Total_cloud_coverService totalCloudCoverService;

    @Autowired
    Vertical_velocityService verticalVelocityService;

    @Autowired
    VisibilityService visibilityService;

    @Autowired
    Wind_speed_gustService windSpeedGustService;

    @Autowired
    MongoServiceImpl mongoService;

    @JmsListener(destination = "grib_data", containerFactory = "jmsQueueListener")
    public void receiveQueue(final Message msg, Session session)
            throws JMSException {
        try{
           if(msg instanceof TextMessage) {
               String jsonPath = ((TextMessage)msg).getText();
               this.paseJsonFile(jsonPath);
           }else if(msg instanceof ObjectMessage){
               ObjectMessage objectMessage = (ObjectMessage)msg;
               JsonQueueFile jsonQueueFile = (JsonQueueFile) objectMessage.getObject();
               this.paseJsonFile(jsonQueueFile.getFileName());
           }
            // 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
            msg.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("grib_data-Queue error：" + e.getLocalizedMessage());
            // 消息处理失败后的重发
            session.recover();

        }
    }

    /**
     * 解析json文件
     * @param jsonPath
     */
    private void paseJsonFile(String jsonPath) {
        String jsonString = FileUtil.readFile(jsonPath);
        JsonArray jArray = Json.createReader(new StringReader(jsonString)).readArray();
        if(jArray==null || jArray.size() == 0) {
            return;
        }
        JsonObject _json = jArray.getJsonObject(0);
        String parameterNumberName = _json.getJsonObject("header").getString("parameterNumberName");
        Date refTime = null;
        try {
            refTime = DateUtil.parseGMT(_json.getJsonObject("header").getString("refTime"));
        } catch (ParseException e) {
            refTime = Calendar.getInstance().getTime();
            e.printStackTrace();
        }
        int forecastTime = _json.getJsonObject("header").getJsonNumber("forecastTime").intValue();
        refTime = DateUtil.getDate(refTime,forecastTime * 60);
        int surface1Value = _json.getJsonObject("header").getJsonNumber("surface1Value").intValue();

        int nx = _json.getJsonObject("header").getJsonNumber("nx").intValue();
        int ny = _json.getJsonObject("header").getJsonNumber("ny").intValue();
        double lo1 = _json.getJsonObject("header").getJsonNumber("lo1").doubleValue();
        double la1 = _json.getJsonObject("header").getJsonNumber("la1").doubleValue();
        double lo2 = _json.getJsonObject("header").getJsonNumber("lo2").doubleValue();
        double la2 = _json.getJsonObject("header").getJsonNumber("la2").doubleValue();
        double dx = _json.getJsonObject("header").getJsonNumber("dx").doubleValue();
        double dy = _json.getJsonObject("header").getJsonNumber("dy").doubleValue();

        List<List<JsonNumber>> datas = new ArrayList<List<JsonNumber>>();
        for(int i=0;i<jArray.size();i++) {
            datas.add(jArray.getJsonObject(i).getJsonArray("data").getValuesAs(JsonNumber.class));
        }
        this.json2girdList(parameterNumberName,refTime,surface1Value,nx,ny,lo1,la1,lo2,la2,dx,dy,datas);
        datas = null;
    }

    /**
     * gribjon 转换位 格点数据
     * @param parameterNumberName
     * @param nx
     * @param ny
     * @param lo1
     * @param la1
     * @param lo2
     * @param la2
     * @param dx
     * @param dy
     */
    public void json2girdList(String parameterNumberName,
                          Date refTime,
                          int surface1Value,
                          int nx,
                          int ny,
                          double lo1,
                          double la1,
                          double lo2,
                          double la2,
                          double dx,
                          double dy,
                          List<List<JsonNumber>> datas) {
        long start = Calendar.getInstance().getTimeInMillis();
        double Δλ = lo2 > lo1 ? dx : -dx;
        double Δφ = la2 > la1 ? dy : -dy;
        int p =0;
        List weathers = new ArrayList();
        for(int j = 0; j < ny ; j++) {
            for(int i = 0; i< nx; i++,p++) {
                double val = 0.d;
                if(datas.size() == 2) {
                    val = new BigDecimal(Math.sqrt(Math.pow(datas.get(0).get(p).doubleValue(),2) + Math.pow(datas.get(1).get(p).doubleValue(),2))).setScale(1, RoundingMode.DOWN).doubleValue();
                }else {
                    val = datas.get(0).get(p).doubleValue();
                }
                double lat = la1 + j * Δφ;
                double lon = lo1 + i * Δλ;
                lon = lon > 180 ? lon - 360: lon;
                weathers.add(WeatherFactory.buildWeather(refTime,surface1Value,lon,lat,val));
            }
        }
        long computer_cost = (Calendar.getInstance().getTimeInMillis() - start);
        start = Calendar.getInstance().getTimeInMillis();
        this.mongoService.insert(parameterNumberName,refTime,surface1Value,weathers);
//        this.saveBatch(parameterNumberName,weathers);
        long save_cost = (Calendar.getInstance().getTimeInMillis() - start);
        logger.info("-" + parameterNumberName + "_" + surface1Value + ",computer_cost:"+ computer_cost +",save_cost:" + save_cost + " millseconds。");
        weathers = null;

    }
    /**
     * 格点数据存储
     * @param parameterNumberName
     * @param weathers
     */
    private void saveBatch(String parameterNumberName, List weathers) {
        switch (parameterNumberName) {
            case "U-component_of_wind":
                this.componentOfWindService.insertBatch(weathers);
                break;
            case "Temperature":
                this.temperatureService.insertBatch(weathers);
                break;
            case "Geopotential_height":
                this.geopotentialHeightService.insertBatch(weathers);
                break;
            case "Ice_water_mixing_ratio":
                this.iceWaterMixingRatioService.insertBatch(weathers);
                break;
            case "Total_precipitation":
                this.totalPrecipitationService.insertBatch(weathers);
                break;
            case "Relative_humidity":
                this.relativeHumidityService.insertBatch(weathers);
                break;
            case "Total_cloud_cover":
                this.totalCloudCoverService.insertBatch(weathers);
                break;
            case "Visibility":
                this.visibilityService.insertBatch(weathers);
                break;
            case "Vertical_velocity":
                this.verticalVelocityService.insertBatch(weathers);
                break;
            case "Wind_speed_gust":
                this.windSpeedGustService.insertBatch(weathers);
                break;
            default:
                ;
        }
    }
}
