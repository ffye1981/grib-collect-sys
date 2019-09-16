package com.zgss.grib.gribservice.service.Impl;

import com.zgss.grib.gribservice.entity.Aggregate;
import com.zgss.grib.gribservice.entity.Grib;
import com.zgss.grib.gribservice.entity.Weather;
import com.zgss.grib.gribservice.service.IBaseService;
import javafx.print.Collation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureServiceImplTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    @Qualifier("TemperatureServiceImpl")
    IBaseService baseService;

    @Test
    public void findOneGrib() throws ParseException {
        Date refTime = sdf.parse("2019-06-21 00:00:00");
        int surfaceValue =  10000;
        Grib list = this.baseService.findOneGrib(refTime,surfaceValue);
        System.out.println("TemperatureServiceImplTest.findOneGrib 找到了" +  list.getHeader().getParameterCategoryName() + "条记录。");
    }

    @Test
    public void listGrids() throws ParseException {
        Date refTime = sdf.parse("2019-08-05 08:00:00");
        int surfaceValue =  10000;
        List<Weather> list = this.baseService.listGrids(refTime,surfaceValue);
        Double max = Double.MIN_VALUE;
        Double min =  Double.MAX_VALUE;
//        double[] arr = new double[list.size()];
        for (Weather w: list) {
//            arr[list.indexOf(w)] = w.getValue();
            max = Math.max(w.getValue(),max);
            min = Math.min(w.getValue(),min);
        }
        System.out.println("max:" +  max + ",min:" + min);
//        max = Arrays.stream(arr).max().getAsDouble();
//        min = Arrays.stream(arr).min().getAsDouble();
//        Collections.max(list);
        System.out.println("max:" +  max + ",min:" + min);
        //max:232.47554,min:218.97554
        //max:232.47554,min:218.97554
    }

    @Test
    public void rangGrids() throws ParseException {
        Date refTime = sdf.parse("2019-08-05 08:00:00");
        int surfaceValue =  10000;
        Aggregate aggregate = this.baseService.rangGrids(refTime,surfaceValue);
        System.out.println(aggregate);
        //Aggregate{sum=0.0, avg=0.0, min=185.17554, max=232.47554}
    }
}