package com.zgss.grib.contour.service.Impl;

import com.zgss.grib.contour.base.BaseServiceImpl;
import com.zgss.grib.contour.entity.Total_cloud_cover;
import com.zgss.grib.contour.entity.Total_cloud_coverKey;
import com.zgss.grib.contour.mapper.Total_cloud_coverMapper;
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

    public String getBaseTable(){
        return "Total_cloud_cover";
    }
}
