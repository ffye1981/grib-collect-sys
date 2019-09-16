package com.zgss.grib.gribservice.service;

import com.mongodb.client.FindIterable;
import com.zgss.grib.gribservice.entity.Aggregate;
import com.zgss.grib.gribservice.entity.Grib;
import com.zgss.grib.gribservice.entity.Weather;
import org.bson.Document;

import java.util.Date;
import java.util.List;

public interface IBaseService {
    Grib findOneGrib(Date refTime, int surfaceValue);
    List<Weather> listGrids(Date refTime,int surfaceValue);
    Aggregate rangGrids(Date refTime, int surfaceValue);
}
