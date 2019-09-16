package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Temperature;
import com.zgss.grib.contour.entity.TemperatureKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemperatureMapper extends BaseMapper<Temperature,TemperatureKey> {
}