package com.zgss.grib.contour.service.Impl;

import com.zgss.grib.contour.base.BaseServiceImpl;
import com.zgss.grib.contour.entity.Total_precipitation;
import com.zgss.grib.contour.entity.Total_precipitationKey;
import com.zgss.grib.contour.mapper.Total_precipitationMapper;
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

    @Override
    public String getBaseTable() {
        return "Total_precipitation";
    }
}
