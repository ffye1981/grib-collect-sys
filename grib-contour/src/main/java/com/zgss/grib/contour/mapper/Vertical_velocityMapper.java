package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Vertical_velocity;
import com.zgss.grib.contour.entity.Vertical_velocityKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Vertical_velocityMapper extends BaseMapper<Vertical_velocity,Vertical_velocityKey> {
}