package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("VisibilityServiceImpl")
public class VisibilityServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Visibility";
    }
}
