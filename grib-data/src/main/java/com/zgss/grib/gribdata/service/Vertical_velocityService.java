package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Vertical_velocity;
import com.zgss.grib.gribdata.entity.Vertical_velocityKey;
import com.zgss.grib.gribdata.mapper.Vertical_velocityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Vertical_velocityService extends BaseServiceImpl<Vertical_velocity, Vertical_velocityKey> {

    @Autowired
    Vertical_velocityMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Vertical_velocityKey getKey(Vertical_velocity record) {
        Vertical_velocityKey key = new Vertical_velocityKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
