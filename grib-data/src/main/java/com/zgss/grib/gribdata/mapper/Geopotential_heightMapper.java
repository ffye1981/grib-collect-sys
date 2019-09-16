package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Component_of_wind;
import com.zgss.grib.gribdata.entity.Component_of_windKey;
import com.zgss.grib.gribdata.entity.Geopotential_height;
import com.zgss.grib.gribdata.entity.Geopotential_heightKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Geopotential_heightMapper extends BaseMapper<Geopotential_height,Geopotential_heightKey> {
}