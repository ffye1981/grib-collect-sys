package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Wind_speed_gust;
import com.zgss.grib.gribdata.entity.Wind_speed_gustKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Wind_speed_gustMapper extends BaseMapper<Wind_speed_gust,Wind_speed_gustKey> {
}