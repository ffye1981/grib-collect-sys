package com.zgss.grib.contour.mapper;

import com.zgss.grib.contour.base.BaseMapper;
import com.zgss.grib.contour.entity.Total_cloud_cover;
import com.zgss.grib.contour.entity.Total_cloud_coverKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Total_cloud_coverMapper extends BaseMapper<Total_cloud_cover,Total_cloud_coverKey> {
}