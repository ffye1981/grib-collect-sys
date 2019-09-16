package com.zgss.girb.gribdownload.service.impl;

import com.netflix.discovery.converters.Auto;
import com.zgss.girb.gribdownload.service.IGribService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GribServiceImplTest {

    @Autowired
    IGribService iGribService;

    @Test
    public void downLoad() {
        iGribService.downLoadFilter();
    }
}