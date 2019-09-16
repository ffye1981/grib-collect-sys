package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("Ice_water_mixing_ratioServiceImpl")
public class Ice_water_mixing_ratioServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Ice_water_mixing_ratio";
    }
}
