package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Component_of_wind;
import com.zgss.grib.contour.entity.Component_of_windKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Component_of_windMapper extends BaseMapper<Component_of_wind,Component_of_windKey> {

}