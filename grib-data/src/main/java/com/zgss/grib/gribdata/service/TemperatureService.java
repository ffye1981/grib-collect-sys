package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Temperature;
import com.zgss.grib.gribdata.entity.TemperatureKey;
import com.zgss.grib.gribdata.mapper.TemperatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TemperatureService extends BaseServiceImpl<Temperature, TemperatureKey> {

    @Autowired
    TemperatureMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public TemperatureKey getKey(Temperature record) {
        TemperatureKey key = new TemperatureKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
