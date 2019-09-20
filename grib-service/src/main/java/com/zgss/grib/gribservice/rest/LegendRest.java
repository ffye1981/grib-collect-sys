package com.zgss.grib.gribservice.rest;

import com.zgss.grib.gribservice.entity.Aggregate;
import com.zgss.grib.gribservice.entity.Grib;
import com.zgss.grib.gribservice.entity.Legend;
import com.zgss.grib.gribservice.entity.Weather;
import com.zgss.grib.gribservice.service.IBaseService;
import com.zgss.grib.gribservice.service.LegendService;
import com.zgss.grib.response.ApiResult;
import com.zgss.grib.response.ResultEnum;
import com.zgss.grib.response.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping({"temperature"})
public class LegendRest {
    private Logger logger = LoggerFactory.getLogger(LegendRest.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    @Qualifier("LegendServiceImpl")
    LegendService legendService;

    @GetMapping({"/listParameterNumberName"})
    @ApiOperation("图例查询服务")
    @ApiImplicitParams({@ApiImplicitParam(
            paramType = "query",
            name = "parameterNumberName",
            dataType = "String",
            required = true,
            value = "要素类型（Temperature|Geopotential_height|Total_precipitation|Relative_humidity|Total_cloud_cover|Visibility|Vertical_velocity|Wind_speed_gust）",
            example = "Temperature"
    )})
    ApiResult listParameterNumberName(@RequestParam(value = "parameterNumberName",required = true) String parameterNumberName){
        try {
            List<Legend> list = this.legendService.listParameterNumberName(parameterNumberName);
            return ResultUtil.build(ResultEnum.SUCCESS, list);
        } catch (Exception var2) {
            this.logger.info("图例查询失败"+ var2.getLocalizedMessage());
            return ResultUtil.error("图例查询失败!" + var2.getMessage());
        }
    }

}
