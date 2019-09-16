package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("TemperatureServiceImpl")
public class TemperatureServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Temperature";
    }
}
