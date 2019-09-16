package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Total_cloud_cover;
import com.zgss.grib.gribdata.entity.Total_cloud_coverKey;
import com.zgss.grib.gribdata.mapper.Total_cloud_coverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Total_cloud_coverService extends BaseServiceImpl<Total_cloud_cover, Total_cloud_coverKey> {

    @Autowired
    Total_cloud_coverMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Total_cloud_coverKey getKey(Total_cloud_cover record) {
        Total_cloud_coverKey key = new Total_cloud_coverKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
