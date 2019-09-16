package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Total_cloud_cover;
import com.zgss.grib.gribdata.entity.Total_cloud_coverKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Total_cloud_coverMapper extends BaseMapper<Total_cloud_cover,Total_cloud_coverKey> {
}