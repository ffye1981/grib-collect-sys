package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("UComponent_of_windServiceImpl")
public class UComponent_of_windServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "U-component_of_wind";
    }
}
