package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.base.BaseServiceImpl;
import com.zgss.grib.gribdata.entity.Visibility;
import com.zgss.grib.gribdata.entity.VisibilityKey;
import com.zgss.grib.gribdata.mapper.VisibilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class VisibilityService extends BaseServiceImpl<Visibility, VisibilityKey> {

    @Autowired
    VisibilityMapper mapper;

    @PostConstruct
    protected void initBase() {
        this.setBaseMapper(mapper);
    }

    @Override
    public VisibilityKey getKey(Visibility record) {
        VisibilityKey key = new VisibilityKey();
        key.setReftime(record.getReftime());
        key.setSurfacevalue(record.getSurfacevalue());
        return key;
    }
}
