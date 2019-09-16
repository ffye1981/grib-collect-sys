package com.zgss.grib.gribservice.service.Impl;

import com.zgss.grib.gribservice.entity.Aggregate;
import com.zgss.grib.gribservice.entity.Grib;
import com.zgss.grib.gribservice.entity.Weather;
import com.zgss.grib.gribservice.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("Component_of_windServiceImpl")
public class Component_of_windServiceIml {

    @Autowired
    @Qualifier("UComponent_of_windServiceImpl")
    IBaseService ubaseService;

    @Autowired
    @Qualifier("VComponent_of_windServiceImpl")
    IBaseService vbaseService;

    public Grib[] findOneGrib(Date refTime, int surfaceValue) {
        Grib ugrib = this.ubaseService.findOneGrib(refTime,surfaceValue);
        Grib vgrib = this.vbaseService.findOneGrib(refTime,surfaceValue);
        return new Grib[]{ugrib,vgrib};
    }

    public List<Weather> listGrids(Date refTime, int surfaceValue) {
        return this.ubaseService.listGrids(refTime,surfaceValue);
    }

    public Aggregate rangGrids(Date refTime, int surfaceValue) {
        return this.ubaseService.rangGrids(refTime,surfaceValue);
    }
}
