package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Vertical_velocity;
import com.zgss.grib.gribdata.entity.Vertical_velocityKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Vertical_velocityMapper extends BaseMapper<Vertical_velocity,Vertical_velocityKey> {
}