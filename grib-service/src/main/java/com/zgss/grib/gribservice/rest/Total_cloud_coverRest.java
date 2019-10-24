package com.zgss.grib.gribservice.rest;

import com.zgss.grib.gribservice.entity.Aggregate;
import com.zgss.grib.gribservice.entity.Grib;
import com.zgss.grib.gribservice.entity.Weather;
import com.zgss.grib.gribservice.service.IBaseService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"total_cloud_cover"})
public class Total_cloud_coverRest {
    private Logger logger = LoggerFactory.getLogger(Total_cloud_coverRest.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    @Qualifier("Total_cloud_coverServiceImpl")
    IBaseService baseService;

    @GetMapping({"/findOneGrib"})
    @ApiOperation("总云量girb数据查询服务")
    @ApiImplicitParams({@ApiImplicitParam(
            paramType = "query",
            name = "refTime",
            dataType = "String",
            required = true,
            value = "时间",
            example = "2019-06-21 00:00:00"
    ), @ApiImplicitParam(
            paramType = "query",
            name = "surfaceValue",
            dataType = "int",
            required = true,
            value = "高度",
            example = "10000"
    )})
    ApiResult findOneGrib(@RequestParam(value = "refTime",required = true) String refTime, @RequestParam(value = "surfaceValue",required = true) int surfaceValue){
        Date refDate = null;
        try {
            refDate = sdf.parse(refTime);
        } catch (ParseException e) {
            return ResultUtil.error("时间格式不正确！");
        }
        try {
            Grib grib = this.baseService.findOneGrib(refDate,surfaceValue);
            return ResultUtil.build(ResultEnum.SUCCESS, grib);
        } catch (Exception var2) {
            this.logger.info("总云量girb数据查询失败"+ var2.getLocalizedMessage());
            return ResultUtil.error("总云量girb数据查询失败!" + var2.getMessage());
        }
    }

    @GetMapping({"/listGrids"})
    @ApiOperation("总云量gird数据查询服务")
    @ApiImplicitParams({@ApiImplicitParam(
            paramType = "query",
            name = "refTime",
            dataType = "String",
            required = true,
            value = "时间",
            example = "2019-06-20 08:00:00"
    ), @ApiImplicitParam(
            paramType = "query",
            name = "surfaceValue",
            dataType = "int",
            required = true,
            value = "高度",
            example = "10000"
    )})
    ApiResult listGrids(@RequestParam(value = "refTime",required = true) String refTime, @RequestParam(value = "surfaceValue",required = true) int surfaceValue){
        Date refDate = null;
        try {
            refDate = sdf.parse(refTime);
        } catch (ParseException e) {
            return ResultUtil.error("时间格式不正确！");
        }
        try {
            List<Weather> list = this.baseService.listGrids(refDate,surfaceValue);
            Aggregate aggregate = this.baseService.rangGrids(refDate,surfaceValue);
            Map result = new HashMap();
            result.put("aggregate",aggregate);
            result.put("data",list);
            return ResultUtil.build(ResultEnum.SUCCESS, result);
        } catch (Exception var2) {
            this.logger.info("总云量gird数据查询失败"+ var2.getLocalizedMessage());
            return ResultUtil.error("总云量gird数据查询失败!" + var2.getMessage());
        }
    }
}
