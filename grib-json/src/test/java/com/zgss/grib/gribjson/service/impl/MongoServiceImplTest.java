package com.zgss.grib.gribjson.service.impl;

import com.zgss.grib.gribjson.service.IMongoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoServiceImplTest {
    @Autowired
    IMongoService mongoService;

    @Test
    public void insertJsonFile() {
        this.mongoService.insertJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190712\\json\\gfs.t00z.pgrb2.1p00.f003_Visibility_0.json","");
//        this.mongoService.insertJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190710\\json\\gfs.t00z.pgrb2.1p00.f000_Temperature_20000.json","");
//        this.mongoService.insertJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190710\\json\\gfs.t00z.pgrb2.1p00.f000_Temperature_30000.json","");
//        this.mongoService.insertJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190710\\json\\gfs.t00z.pgrb2.1p00.f000_Temperature_50000.json","");
//        this.mongoService.insertJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190710\\json\\gfs.t00z.pgrb2.1p00.f000_Temperature_70000.json","");
//        this.mongoService.insertJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190710\\json\\gfs.t00z.pgrb2.1p00.f000_Temperature_85000.json","");
//        this.mongoService.insertJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190710\\json\\gfs.t00z.pgrb2.1p00.f000_Temperature_100000.json","");
    }
}