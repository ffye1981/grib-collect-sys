package com.zgss.grib.contour.service.Impl;

import com.zgss.grib.contour.base.BaseServiceImpl;
import com.zgss.grib.contour.entity.Temperature;
import com.zgss.grib.contour.entity.TemperatureKey;
import com.zgss.grib.contour.mapper.TemperatureMapper;
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

    @Override
    public String getBaseTable() {
        return "Temperature";
    }
}
