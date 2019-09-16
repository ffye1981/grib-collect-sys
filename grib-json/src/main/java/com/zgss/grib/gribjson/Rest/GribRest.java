package com.zgss.grib.gribjson.Rest;

import com.zgss.grib.gribjson.service.IGribParseService;
import com.zgss.grib.gribjson.service.impl.GribParserServiceImpl;
import com.zgss.grib.response.ApiResult;
import com.zgss.grib.response.ResultEnum;
import com.zgss.grib.response.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("grib")
public class GribRest {
    private Logger logger = LoggerFactory.getLogger(GribRest.class);

    Timer timer = new Timer();

    @Autowired
    IGribParseService gribParseService;

    @GetMapping({"/toJson"})
    @ApiOperation("GRIB文件解析服务")
    @Async
    @ApiImplicitParams({@ApiImplicitParam(
            paramType = "query",
            name = "dataRoot",
            dataType = "String",
            required = true,
            value = "数据目录",
            example = ""
    )})
    ApiResult getByPlateNumber(@RequestParam(value = "dataRoot",required = true) String dataRoot){
        try {
            if(dataRoot!=null && !dataRoot.isEmpty()) {
                ((GribParserServiceImpl)this.gribParseService).setDataRoot(dataRoot);
            }
            this.gribParseService.parse();
            return ResultUtil.build(ResultEnum.SUCCESS, "GRIB文件解析服务完成");
        } catch (Exception var2) {
            this.logger.info("GRIB文件解析失败");
            return ResultUtil.error("GRIB文件解析失败");
        }

    }
}
