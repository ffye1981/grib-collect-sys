package com.zgss.grib.contour.service;

import com.zgss.grib.contour.entity.Temperature;
import com.zgss.grib.contour.service.Impl.TemperatureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureServiceTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    TemperatureService temperatureService;

    @Test
    public void insert() {
        Temperature t = new Temperature();
        t.setLon(0d);
        t.setLat(0d);
        try {
            t.setReftime(sdf.parse("2019-08-29 11:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        t.setValue(new Double(227));
        t.setSurfacevalue(new Integer(20000));
        t.setGeojson("{\"type\":\"Point\",\"coordinates\":[0,90]}");
        temperatureService.insert(t);

    }

    @Test
    public void existsTable() {
        try {
            Date refTime = sdf.parse("2019-08-29 11:00:00");
            boolean exists = this.temperatureService.existsTable(refTime,10000,true);
            System.out.println("table" + (exists?"存在":"不存在"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subsTable() {
        List<String> subTables = this.temperatureService.getSubTables();
        for (String tableName: subTables) {
            System.out.println(tableName);
        }
    }
}