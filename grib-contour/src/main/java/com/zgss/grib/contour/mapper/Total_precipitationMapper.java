package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Total_precipitation;
import com.zgss.grib.contour.entity.Total_precipitationKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Total_precipitationMapper extends BaseMapper<Total_precipitation,Total_precipitationKey> {
}