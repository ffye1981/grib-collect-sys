package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("Vertical_velocityServiceImpl")
public class Vertical_velocityServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Vertical_velocity";
    }
}
