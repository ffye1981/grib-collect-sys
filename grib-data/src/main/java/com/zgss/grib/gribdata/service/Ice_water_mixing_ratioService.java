package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Ice_water_mixing_ratio;
import com.zgss.grib.gribdata.entity.Ice_water_mixing_ratioKey;
import com.zgss.grib.gribdata.mapper.Ice_water_mixing_ratioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Ice_water_mixing_ratioService extends BaseServiceImpl<Ice_water_mixing_ratio, Ice_water_mixing_ratioKey> {

    @Autowired
    Ice_water_mixing_ratioMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Ice_water_mixing_ratioKey getKey(Ice_water_mixing_ratio record) {
        Ice_water_mixing_ratioKey key = new Ice_water_mixing_ratioKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
