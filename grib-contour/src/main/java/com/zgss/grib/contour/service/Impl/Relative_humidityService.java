package com.zgss.grib.contour.service.Impl;

import com.zgss.grib.contour.base.BaseServiceImpl;
import com.zgss.grib.contour.entity.Relative_humidity;
import com.zgss.grib.contour.entity.Relative_humidityKey;
import com.zgss.grib.contour.mapper.Relative_humidityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Relative_humidityService extends BaseServiceImpl<Relative_humidity, Relative_humidityKey> {

    @Autowired
    Relative_humidityMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Relative_humidityKey getKey(Relative_humidity record) {
        Relative_humidityKey key = new Relative_humidityKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }

    @Override
    public String getBaseTable() {
        return "Relative_humidity";
    }
}
