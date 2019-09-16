package com.zgss.grib.contour.service;

import com.zgss.grib.common.util.DateUtil;
import com.zgss.grib.common.util.FileUtil;
import com.zgss.grib.contour.util.ArrayUtil;
import com.zgss.grib.entity.JsonQueueFile;
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
    ContourService contourService;

    @JmsListener(destination = "grib_contour", containerFactory = "jmsQueueListener")
    public void receiveQueue(final Message msg, Session session)
            throws JMSException {
        String jsonPath = null;
        try{
           if(msg instanceof TextMessage) {
               jsonPath = ((TextMessage)msg).getText();
               this.paseJsonFile(jsonPath);
           }else if(msg instanceof ObjectMessage){
               ObjectMessage objectMessage = (ObjectMessage)msg;
               JsonQueueFile jsonQueueFile = (JsonQueueFile) objectMessage.getObject();
               long start = Calendar.getInstance().getTimeInMillis();
               jsonPath = jsonQueueFile.getFileName();
               this.paseJsonFile(jsonPath);
           }
            // 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
            msg.acknowledge();
        } catch (Exception e) {
            logger.info("grib_contour-Queue error：：path=" +jsonPath + e.getLocalizedMessage());
            e.printStackTrace();
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
        if(parameterNumberName.equals("Component_of_wind")) {
            //风和阵风不生成等值面
            return;
        }
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
        this.json2girdList2(parameterNumberName,refTime,surface1Value,nx,ny,lo1,la1,lo2,la2,dx,dy,datas);
        datas = null;
        jArray = null;
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
//    public void json2girdList(String parameterNumberName,
//                               Date refTime,
//                               int surface1Value,
//                               int nx,
//                               int ny,
//                               double lo1,
//                               double la1,
//                               double lo2,
//                               double la2,
//                               double dx,
//                               double dy,
//                               List<List<JsonNumber>> datas) {
//
//        double[] lons = new double[nx*ny];
//        double[] lats = new double[nx*ny];
//        double[] values = new double[nx*ny];
//        double[][] gridData = new double[][]{
//                lons,lats,values
//        };
//        double Δλ = lo2 > lo1 ? dx : -dx;
//        double Δφ = la2 > la1 ? dy : -dy;
//        int p = 0;
//        List weathers = new ArrayList();
//        for(double j = 0; j < ny ; j = j + dy) {
//            for(double i = 0; i< nx; i = i + dx,p++) {
//                double val = 0.d;
//                if(datas.size() == 2) {
//                    val = new BigDecimal(Math.sqrt(Math.pow(datas.get(0).get(p).doubleValue(),2) + Math.pow(datas.get(1).get(p).doubleValue(),2))).setScale(1, RoundingMode.DOWN).doubleValue();
//                }else {
//                    val = datas.get(0).get(p).doubleValue();
//                }
//                double lon = lo1 + i * Δλ;
//                double lat = la1 + j * Δφ;
////                lon = lon > 180 ? lon - 360: lon;
//                lons[p] = lon;
//                lats[p] = lat;
//                values[p] = val;
//            }
//        }
//        long start = Calendar.getInstance().getTimeInMillis();
//        contourService.calEquiSurface(parameterNumberName,refTime,surface1Value,gridData,lons,lats);
//        long delay = (Calendar.getInstance().getTimeInMillis() - start);
//        logger.info("JsonMsgConsumer-save:" + parameterNumberName + ",耗时：" + delay + "毫秒。");
//        weathers = null;
//
//    }
    public void json2girdList2(String parameterNumberName,
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

        double[] lons = new double[nx];
        double[] lats = new double[ny];
        double[][] gridData = new double[ny][nx];
        double Δλ = lo2 > lo1 ? dx : -dx;
        double Δφ = la2 > la1 ? dy : -dy;

        for(int i = 0; i< nx; i++) {
            double lon = lo1 + i * Δλ;
//            lon = lon > 180 ? lon - 360: lon;
            lons[i] = lon;
        }

        for(int j = 0; j < ny ; j++) {
            double lat = la1 + j * Δφ;
            lats[j] = lat;
        }
        int p = 0;
        List weathers = new ArrayList();
        double min =  100000;
        double max =  -100000;
        for(int j = 0; j < ny ; j++) {
            for(int i = 0; i< nx; i++,p++) {
                double val = 0.d;
                if(datas.size() == 2) {
                    val = new BigDecimal(Math.sqrt(Math.pow(datas.get(0).get(p).doubleValue(),2) + Math.pow(datas.get(1).get(p).doubleValue(),2))).setScale(1, RoundingMode.DOWN).doubleValue();
                }else {
                    val = datas.get(0).get(p).doubleValue();
                }
                min = Math.min(min,val);
                max = Math.max(max,val);
                gridData[j][i] = val;
            }
        }
        System.out.println("surface1Value="+ surface1Value+ ",min:" + min + ",max:" + max);
        long start = Calendar.getInstance().getTimeInMillis();
        ArrayUtil.reverseArray(lats);
        ArrayUtil.reverseArray(gridData);
        contourService.calEquiSurface2(parameterNumberName,refTime,surface1Value,gridData,lons,lats,min,max);
        weathers = null;
        gridData = null;
    }
}
