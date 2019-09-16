package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Visibility;
import com.zgss.grib.contour.entity.VisibilityKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VisibilityMapper extends BaseMapper<Visibility,VisibilityKey> {
}