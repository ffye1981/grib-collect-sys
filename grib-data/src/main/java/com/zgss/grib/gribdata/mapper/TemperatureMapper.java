package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Temperature;
import com.zgss.grib.gribdata.entity.TemperatureKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemperatureMapper extends BaseMapper<Temperature,TemperatureKey> {
}