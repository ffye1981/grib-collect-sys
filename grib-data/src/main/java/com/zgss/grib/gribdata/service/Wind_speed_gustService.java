package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Wind_speed_gust;
import com.zgss.grib.gribdata.entity.Wind_speed_gustKey;
import com.zgss.grib.gribdata.mapper.Wind_speed_gustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Wind_speed_gustService extends BaseServiceImpl<Wind_speed_gust, Wind_speed_gustKey> {

    @Autowired
    Wind_speed_gustMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Wind_speed_gustKey getKey(Wind_speed_gust record) {
        Wind_speed_gustKey key = new Wind_speed_gustKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
