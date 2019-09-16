package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Wind_speed_gust;
import com.zgss.grib.contour.entity.Wind_speed_gustKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Wind_speed_gustMapper extends BaseMapper<Wind_speed_gust,Wind_speed_gustKey> {
}