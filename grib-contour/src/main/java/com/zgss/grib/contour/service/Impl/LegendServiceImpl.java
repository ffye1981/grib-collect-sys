package com.zgss.grib.contour.service.Impl;

import com.zgss.grib.contour.entity.Legend;
import com.zgss.grib.contour.mapper.LegendMapper;
import com.zgss.grib.contour.service.LegendService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: grib-collect-sys
 * @description: 图例服务实现类
 * @author: ffye
 * @create: 2019-09-02 10:12
 */
@Service
public class LegendServiceImpl implements LegendService {

    @Autowired
    LegendMapper legendMapper;

    @Override
    public List<Legend> listParameterNumberName(String parameterNumberName) {
        Map<String, Object> param = new HashMap<>();
        if(parameterNumberName!=null && !parameterNumberName.isEmpty()) {
            param.put("parameterNumberName",parameterNumberName);
        }
        return legendMapper.selectAll(param);
    }

    @Override
    @Cacheable(value = "legends", key="#parameterNumberName")
    public double[] listBreaks(String parameterNumberName) {
        int index = 0;
        Double[] doubles =  legendMapper.selectBreaks(parameterNumberName);
        double[] _breaks = new double[doubles.length];
        while(index < doubles.length) {
            _breaks[index] = doubles[index].doubleValue();
            index ++;
        }
        return _breaks;
    }

    @Override
    public int insert(Legend record) {
        return this.legendMapper.insert(record);
    }
}
