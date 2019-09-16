package com.zgss.grib.contour.service.Impl;

import com.zgss.grib.contour.entity.Legend;
import com.zgss.grib.contour.service.LegendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LegendServiceImplTest {

    @Autowired
    LegendService legendService;

    @Test
    public void listParameterNumberName() {
        List<Legend> legends = this.legendService.listParameterNumberName("Temperature");
        for (Legend l: legends) {
            System.out.println("legend:" + l);
        }
    }

    @Test
    public void listBreaks() {
        double[] breaks = this.legendService.listBreaks("Temperature");
        breaks = this.legendService.listBreaks("Temperature");
        breaks = this.legendService.listBreaks("Temperature");
        breaks = this.legendService.listBreaks("Temperature");
        breaks = this.legendService.listBreaks("Temperature");
        for (double l: breaks) {
            System.out.println("legend:" + l);
        }
    }

    @Test
    public void insert() {
        int i = 0;
        int value = 10000;
        while (value <=13000){
            Legend l = new Legend();
            l.setParameternumbername("Geopotential_height20000");
            l.setCaption(new Integer(value).toString());
            l.setColorhex("#000000");
            l.setMin(new Double(value).doubleValue());
            l.setOrder(i);
            this.legendService.insert(l);
            value = value+ 40;
            i++;
        }
    }
}