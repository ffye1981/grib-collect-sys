package com.zgss.grib.gribdata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Delayed;

@Service
public class MaintainTask {
    private Logger logger = LoggerFactory.getLogger(MaintainTask.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    MongoServiceImpl mongoService;

    @Scheduled(cron = "${cron.maintain}")
    public void removeExpireData() {
        Set<String> collectionNames = this.mongoService.getAllCollectionNames();
        Iterator<String> it = collectionNames.iterator();
        while(it.hasNext()) {
            String collectionName = it.next().toString();
            int postion = collectionName.lastIndexOf("_");
            if(postion > -1) {
                String postTime = collectionName.substring(collectionName.lastIndexOf("_") + 1);
                try {
                    Date postDate = sdf.parse(postTime);
                    if(Calendar.getInstance().getTimeInMillis() - postDate.getTime() > 24 * 60 * 60 * 1000) {
                        this.mongoService.removeCollection(collectionName);
                        logger.info("删除过期集合：" + collectionName);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                System.out.println(collectionName+ ":" +postTime);
            }

        }
    }
}
