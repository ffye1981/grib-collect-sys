package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("VComponent_of_windServiceImpl")
public class VComponent_of_windServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "V-component_of_wind";
    }
}
