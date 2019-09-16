package com.zgss.grib.gribservice.service.Impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.zgss.grib.common.util.GribUtil;
import com.zgss.grib.gribservice.entity.Aggregate;
import com.zgss.grib.gribservice.entity.Grib;
import com.zgss.grib.gribservice.entity.Weather;
import com.zgss.grib.gribservice.service.IBaseService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class BaseServiceImpl implements IBaseService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Grib findOneGrib(Date refTime, int surfaceValue) {
        Criteria criteria = Criteria.where("refTime").is(refTime);
        criteria.and("header.surface1Value").is(surfaceValue);
        String collectName = this.getGribCollectionName(refTime,surfaceValue);
        return this.mongoTemplate.findOne(new Query(criteria), Grib.class,this.getGribCollectionName(refTime,surfaceValue));
    }

    @Override
    public List<Weather> listGrids(Date refTime, int surfaceValue) {
        Criteria criteria = Criteria.where("reftime").is(refTime);
        criteria.and("surfacevalue").is(surfaceValue);
        return this.mongoTemplate.find(new Query(criteria), Weather.class,this.getGridCollectionName(refTime,surfaceValue));
    }

    @Override
    public Aggregate rangGrids(Date refTime, int surfaceValue) {
        Criteria criteria = Criteria.where("reftime").is(refTime);
        criteria.and("surfacevalue").is(surfaceValue);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("null").max("value").as("max").min("value").as("min")
        );
        Aggregate agg = null;
        AggregationResults<Aggregate> results =  this.mongoTemplate.aggregate(aggregation,this.getGridCollectionName(refTime,surfaceValue), Aggregate.class);
        Iterator<Aggregate> it = results.iterator();
        if(it.hasNext()) {
            agg = it.next();
        }
        return agg;
    }

    protected  String getGribCollectionName(Date refTime, int surfaceValue){
        String parameterNumberName = this.getParameterNumberName();
        return GribUtil.getGribName(parameterNumberName,refTime,surfaceValue);
    }

    protected  String getGridCollectionName(Date refTime, int surfaceValue){
        String parameterNumberName = this.getParameterNumberName();
        return GribUtil.getGridName(parameterNumberName,refTime,surfaceValue);
    }
    protected abstract String getParameterNumberName();
}
