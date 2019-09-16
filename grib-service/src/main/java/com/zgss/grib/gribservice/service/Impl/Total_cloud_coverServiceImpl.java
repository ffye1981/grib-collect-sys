package com.zgss.grib.gribservice.service.Impl;

import org.springframework.stereotype.Service;

@Service("Total_cloud_coverServiceImpl")
public class Total_cloud_coverServiceImpl extends BaseServiceImpl {
    @Override
    protected String getParameterNumberName() {
        return "Total_cloud_cover";
    }
}
