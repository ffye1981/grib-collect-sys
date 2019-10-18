package com.zgss.grib.gribjson.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.util.JSON;
import com.zgss.grib.common.util.DateUtil;
import com.zgss.grib.common.util.FileUtil;
import com.zgss.grib.common.util.GribUtil;
import com.zgss.grib.gribjson.service.IMongoService;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class MongoServiceImpl implements IMongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private static UpdateOptions updateOne;
    static {
        updateOne = new UpdateOptions();
        updateOne.upsert(true);
    }

    @Override
    public void insertJsonFile(String jsonPath,String subject) {
        String jsonString = FileUtil.readFile(jsonPath);
        JsonArray jArray = Json.createReader(new StringReader(jsonString)).readArray();
        for(int i=0;i< 1;i++) {
            JsonObject _json = jArray.getJsonObject(i);
            String collectionName = _json.getJsonObject("header").getString("parameterNumberName");
            JsonNumber surface1Value = _json.getJsonObject("header").getJsonNumber("surface1Value");
            String refTimeStr = _json.getJsonObject("header").getString("refTime");

            Date refTime = null;
            try {
                refTime = DateUtil.parseGMT(refTimeStr);
            } catch (ParseException e) {
                refTime = Calendar.getInstance().getTime();
                e.printStackTrace();
            }
            int forecastTime = _json.getJsonObject("header").getJsonNumber("forecastTime").intValue();
            refTime = DateUtil.getDate(refTime,forecastTime * 60);
            //collection创建
            collectionName = GribUtil.getGribName(collectionName,refTime,surface1Value.intValue());
            if(!this.mongoTemplate.collectionExists(collectionName)) {
                this.mongoTemplate.createCollection(collectionName);
                IndexOperations io = this.mongoTemplate.indexOps(collectionName);
                Index index =new Index();
                index.on("refTime", Sort.Direction.ASC);
                index.on("header.surface1Value", Sort.Direction.ASC);
                index.named("refTime_surface1Value");
                io.ensureIndex(index);
            }
            //如果存在时间和高度相同的记录更新，反之插入
            Document document = Document.parse(_json.toString());
            document.put("refTime",refTime);
            Criteria criteria = Criteria.where("refTime").is(refTime);
            criteria.andOperator(Criteria.where("header.surface1Value").is(Double.parseDouble(surface1Value.toString())));
            Query query = new Query(criteria);
            this.mongoTemplate.upsert(query, Update.fromDocument(document,new String[]{}),collectionName);
        }
    }
}
