package com.zgss.grib.contour.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaintainTaskTest {

    @Autowired
    MaintainTask maintainTask;

    @Test
    public void removeExpireData() {
        maintainTask.removeExpireData();
    }
}