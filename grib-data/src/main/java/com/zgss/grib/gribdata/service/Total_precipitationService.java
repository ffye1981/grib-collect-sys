package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Total_precipitation;
import com.zgss.grib.gribdata.entity.Total_precipitationKey;
import com.zgss.grib.gribdata.mapper.Total_precipitationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Total_precipitationService extends BaseServiceImpl<Total_precipitation, Total_precipitationKey> {

    @Autowired
    Total_precipitationMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Total_precipitationKey getKey(Total_precipitation record) {
        Total_precipitationKey key = new Total_precipitationKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
