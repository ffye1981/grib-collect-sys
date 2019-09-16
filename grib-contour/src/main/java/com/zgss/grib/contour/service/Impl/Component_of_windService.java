package com.zgss.grib.contour.service.Impl;

import com.zgss.grib.contour.base.BaseServiceImpl;
import com.zgss.grib.contour.entity.Component_of_wind;
import com.zgss.grib.contour.entity.Component_of_windKey;
import com.zgss.grib.contour.mapper.Component_of_windMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Component_of_windService extends BaseServiceImpl<Component_of_wind, Component_of_windKey> {

    @Autowired
    Component_of_windMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public Component_of_windKey getKey(Component_of_wind record) {
        Component_of_windKey key = new Component_of_windKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }

    public String getBaseTable(){
        return "Component_of_wind";
    }
}
