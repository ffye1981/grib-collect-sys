package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("Geopotential_heightServiceImpl")
public class Geopotential_heightServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Geopotential_height";
    }
}
