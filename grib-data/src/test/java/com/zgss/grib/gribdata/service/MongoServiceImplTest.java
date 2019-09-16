package com.zgss.grib.gribdata.service;

import com.mongodb.client.result.DeleteResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoServiceImplTest {
    @Autowired
    MongoServiceImpl mongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Test
    public void upSert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String parameterNumberName = "Component_of_wind";
        Date refTime = null;
        try {
            refTime = sdf.parse("20190724");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int surface1Value = 10000;
        double lon = 0;
        double lat = 90;
        double value = 16.38;
        this.mongoService.upSert(parameterNumberName,refTime,surface1Value,lon,lat,value);
        this.mongoService.upSert(parameterNumberName,refTime,surface1Value,lon,lat,30);
        this.mongoService.upSert(parameterNumberName,refTime,surface1Value,lon,89.5,value);
    }

    @Test
    public void delete() {
        Criteria criteria = null;
        try {
            criteria = Criteria.where("reftime").is(sdf.parse("2019-06-20 08:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        criteria.and("surfacevalue").is(10000);
        Query query = new Query(criteria);
        DeleteResult dr = this.mongoTemplate.remove(query,"temperature");
        System.out.println("删除了：" + dr.getDeletedCount());
    }
}