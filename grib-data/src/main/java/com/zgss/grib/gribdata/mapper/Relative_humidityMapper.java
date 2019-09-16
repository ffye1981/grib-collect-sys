package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Relative_humidity;
import com.zgss.grib.gribdata.entity.Relative_humidityKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Relative_humidityMapper extends BaseMapper<Relative_humidity,Relative_humidityKey> {
}