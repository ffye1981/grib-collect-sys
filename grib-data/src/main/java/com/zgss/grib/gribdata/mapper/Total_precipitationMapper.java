package com.zgss.grib.gribdata.mapper;

import com.zgss.grib.gribdata.base.BaseMapper;
import com.zgss.grib.gribdata.entity.Total_precipitation;
import com.zgss.grib.gribdata.entity.Total_precipitationKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Total_precipitationMapper extends BaseMapper<Total_precipitation,Total_precipitationKey> {
}