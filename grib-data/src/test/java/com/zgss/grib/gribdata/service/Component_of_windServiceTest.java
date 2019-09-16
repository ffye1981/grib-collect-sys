package com.zgss.grib.gribdata.service;

import com.zgss.grib.gribdata.entity.Component_of_wind;
import com.zgss.grib.gribdata.entity.Component_of_windKey;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Component_of_windServiceTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    Component_of_windService component_of_windService;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        for (int i=0;i<6000;i++){
            Component_of_wind wind = new Component_of_wind();
            wind.setLon(new Double(0));
            wind.setLat(new Double(89.50));
            wind.setValue(new Double(i));
            try {
                wind.setReftime(sdf.parse("2019-07-02 11:00:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            wind.setSurfacevalue(new Integer(20000));
            this.component_of_windService.insert(wind);
        }

    }
    @Test
    public void insertBatch() {
        List<Component_of_wind> dataList = new ArrayList<Component_of_wind>();
        for (int i=0;i<6000;i++){
            Component_of_wind wind = new Component_of_wind();
            wind.setLon(new Double(0));
            wind.setLat(new Double(89.50));
            wind.setValue(new Double(i));
            try {
                wind.setReftime(sdf.parse("2019-07-03 11:00:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            wind.setSurfacevalue(new Integer(20000));
            dataList.add(wind);
        }
        this.component_of_windService.insertBatch(dataList);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        Component_of_windKey key = new Component_of_windKey();
        try {
            key.setReftime(sdf.parse("2019-07-02 11:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        key.setSurfacevalue(new Integer(10000));
        Component_of_wind exists = this.component_of_windService.selectByPrimaryKey(key);
        System.out.println("Component_of_windServiceTest-exist:" + exists);
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    @Ignore
    public void exists() {
        Component_of_windKey key = new Component_of_windKey();
        try {
            key.setReftime(sdf.parse("2019-07-02 11:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        key.setSurfacevalue(new Integer(10000));
        boolean exists = this.component_of_windService.exists(key);
        System.out.println("Component_of_windServiceTest-exist:" + exists);
    }

    @Test
    public void upSert() {
        Component_of_wind wind = new Component_of_wind();
        wind.setLon(new Double(0));
        wind.setLat(new Double(89.50));
        wind.setValue(new Double(0.39));
        try {
            wind.setReftime(sdf.parse("2019-07-02 11:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        wind.setSurfacevalue(new Integer(20000));
        this.component_of_windService.upSert(wind);
    }

    @Test
    public void upSert1() {
    }
}