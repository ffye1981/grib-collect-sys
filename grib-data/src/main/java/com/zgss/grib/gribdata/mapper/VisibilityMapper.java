package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Visibility;
import com.zgss.grib.gribdata.entity.VisibilityKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisibilityMapper extends BaseMapper<Visibility,VisibilityKey> {
}