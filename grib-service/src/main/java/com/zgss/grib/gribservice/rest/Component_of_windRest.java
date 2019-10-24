package com.zgss.grib.gribservice.rest;

import com.zgss.grib.gribservice.entity.Aggregate;
import com.zgss.grib.gribservice.entity.Grib;
import com.zgss.grib.gribservice.entity.Weather;
import com.zgss.grib.gribservice.service.Impl.Component_of_windServiceIml;
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
@RequestMapping({"component_of_wind"})
public class Component_of_windRest {
    private Logger logger = LoggerFactory.getLogger(Component_of_windRest.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    @Qualifier("Component_of_windServiceImpl")
    Component_of_windServiceIml baseService;

    @GetMapping({"/findOneGrib"})
    @ApiOperation("风girb数据查询服务")
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
            Grib[] ugrib = this.baseService.findOneGrib(refDate,surfaceValue);
            return ResultUtil.build(ResultEnum.SUCCESS, ugrib);
        } catch (Exception var2) {
            this.logger.info("风girb数据查询失败"+ var2.getLocalizedMessage());
            return ResultUtil.error("风girb数据查询失败!" + var2.getMessage());
        }
    }

    @GetMapping({"/listGrids"})
    @ApiOperation("风gird数据查询服务")
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
            long start = Calendar.getInstance().getTimeInMillis();
            List<Weather> list = this.baseService.listGrids(refDate,surfaceValue);
            Aggregate aggregate = this.baseService.rangGrids(refDate,surfaceValue);
            long delay = (Calendar.getInstance().getTimeInMillis() - start);
            logger.info("listGrids:"+ ",耗时：" + delay + "毫秒。");
            Map result = new HashMap();
            result.put("aggregate",aggregate);
            result.put("data",list);
            return ResultUtil.build(ResultEnum.SUCCESS, result);
        } catch (Exception var2) {
            this.logger.info("风gird数据查询失败"+ var2.getLocalizedMessage());
            return ResultUtil.error("风gird数据查询失败!" + var2.getMessage());
        }
    }
}
