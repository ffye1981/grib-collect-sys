package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("Total_precipitationServiceImpl")
public class Total_precipitationServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Total_precipitation";
    }
}
