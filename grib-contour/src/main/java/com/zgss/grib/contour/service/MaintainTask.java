package com.zgss.grib.contour.service;

import com.zgss.grib.contour.service.Impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MaintainTask {
    private Logger logger = LoggerFactory.getLogger(MaintainTask.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    Geopotential_heightService geopotentialHeightService;


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

    @Scheduled(cron = "${cron.maintain}")
    public void removeExpireData() {
        List<String> subTables = this.geopotentialHeightService.getSubTables();
        this.removeExpireData(subTables);
        subTables = this.relativeHumidityService.getSubTables();
        this.removeExpireData(subTables);
        subTables = this.temperatureService.getSubTables();
        this.removeExpireData(subTables);
        subTables = this.totalCloudCoverService.getSubTables();
        this.removeExpireData(subTables);
        subTables = this.verticalVelocityService.getSubTables();
        this.removeExpireData(subTables);
        subTables = this.windSpeedGustService.getSubTables();
        this.removeExpireData(subTables);
        subTables = this.visibilityService.getSubTables();
        this.removeExpireData(subTables);
    }
    public void removeExpireData(List<String> tables) {

        for (String tableName: tables) {
            int postion = tableName.lastIndexOf("_");
            if(postion > -1) {
                String postTime = tableName.substring(tableName.lastIndexOf("_") + 1);
                try {
                    Date postDate = sdf.parse(postTime);
                    if(Calendar.getInstance().getTimeInMillis() - postDate.getTime() > 24 * 60 * 60 * 1000) {
                        this.temperatureService.removeSubTable(tableName);
                        logger.info("删除过期集合：" + tableName);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                System.out.println(collectionName+ ":" +postTime);
            }
        }

    }

}
