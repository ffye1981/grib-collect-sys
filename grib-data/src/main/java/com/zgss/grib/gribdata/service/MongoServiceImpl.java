package com.zgss.grib.gribdata.service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.client.result.DeleteResult;
import com.zgss.grib.common.util.GribUtil;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class MongoServiceImpl{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private MongoTemplate mongoTemplate;

    private static UpdateOptions updateOne;
    static {
        updateOne = new UpdateOptions();
        updateOne.upsert(true);
    }

    public void upSert(String parameterNumberName,
                       Date refTime,
                       int surface1Value,
                       double lon,
                       double lat,
                       double value) {
        Point point = new Point(new Position(lon,lat));
        String _id = sdf.format(refTime) + "_" + lon + "_" + lat + "_"+ surface1Value;
        Criteria criteria = Criteria.where("_id").is(_id);
//        Criteria criteria = Criteria.where("refTime").is(refTime);
//        criteria.and("surface1Value").is(new Double(surface1Value));
//        criteria.and("lon").is(lon);
//        criteria.and("lat").is(lat);
        Query query = new Query(criteria);

        Update update = new Update();
        update.set("_id",_id);
        update.set("refTime",refTime);
        update.set("surface1Value",new Double(surface1Value));
        update.set("lon",lon);
        update.set("lat",lat);
        update.set("value",value);
        update.set("location",JSONObject.parseObject(point.toJson()));
        this.mongoTemplate.upsert(query,update,parameterNumberName + "_detail");  //30秒
    }

    public void insert(String parameterNumberName,
                       Date refTime,
                       int surface1Value,
                       List datas) {
        String collectionName = GribUtil.getGridName(parameterNumberName,refTime,surface1Value);
        if(!this.mongoTemplate.collectionExists(collectionName)) {
            this.mongoTemplate.createCollection(collectionName);
//            IndexOperations io = this.mongoTemplate.indexOps(collectionName);
//            Index index =new Index();
//            index.on("refTime", Sort.Direction.ASC);
//            index.on("surfaceValue", Sort.Direction.ASC);
//            index.named("refTime_surfaceValue");
//            io.ensureIndex(index);
//            GeospatialIndex gi = new GeospatialIndex("location");
//            gi.typed(GeoSpatialIndexType.GEO_2DSPHERE);
//            gi.named("location");
//            io.ensureIndex(gi);
        }
        Criteria criteria = Criteria.where("reftime").is(refTime);
        criteria.and("surfacevalue").is(new Integer(surface1Value));
        Query query = new Query(criteria);
        DeleteResult dr = this.mongoTemplate.remove(query,collectionName);
        this.mongoTemplate.insert(datas,collectionName);
    }

    public Set<String> getAllCollectionNames() {
        return this.mongoTemplate.getCollectionNames();
    }

    public void removeCollection(String collectionName) {
        this.mongoTemplate.dropCollection(collectionName);
    }
}
