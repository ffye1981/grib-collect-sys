package com.zgss.grib.gribservice.service;

import com.zgss.grib.gribservice.entity.Legend;

import java.util.List;

/**
 * 图例服务接口
 */
public interface LegendService {
    /**
     * 获取指定要素类型的图例列表
     * @param parameterNumberName
     * @return
     */
    List<Legend> listParameterNumberName(String parameterNumberName);

    /**
     * 获取指定要素类型分级点
     * @param parameterNumberName
     * @return
     */
    double[] listBreaks(String parameterNumberName);
    int insert(Legend record);
}
