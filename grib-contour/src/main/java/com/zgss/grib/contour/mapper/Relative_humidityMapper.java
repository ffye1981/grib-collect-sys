package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Relative_humidity;
import com.zgss.grib.contour.entity.Relative_humidityKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Relative_humidityMapper extends BaseMapper<Relative_humidity,Relative_humidityKey> {
}