package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Geopotential_height;
import com.zgss.grib.gribdata.entity.Geopotential_heightKey;
import com.zgss.grib.gribdata.mapper.Geopotential_heightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Geopotential_heightService extends BaseServiceImpl<Geopotential_height, Geopotential_heightKey> {

    @Autowired
    Geopotential_heightMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Geopotential_heightKey getKey(Geopotential_height record) {
        Geopotential_heightKey key = new Geopotential_heightKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
