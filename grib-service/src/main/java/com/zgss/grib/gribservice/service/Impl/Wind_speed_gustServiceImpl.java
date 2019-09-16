package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("Wind_speed_gustServiceImpl")
public class Wind_speed_gustServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Wind_speed_gust";
    }
}
