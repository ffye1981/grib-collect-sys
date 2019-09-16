package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Geopotential_height;
import com.zgss.grib.contour.entity.Geopotential_heightKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Geopotential_heightMapper extends BaseMapper<Geopotential_height,Geopotential_heightKey> {
}