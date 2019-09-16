package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("Relative_humidityServiceImpl")
public class Relative_humidityServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Relative_humidity";
    }
}
