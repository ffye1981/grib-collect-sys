package com.zgss.grib.gribjson.service;

import com.zgss.grib.entity.JsonQueueFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonMsgProducerTest {

    @Autowired
    JsonMsgProducer jsonMsgProducer;

    @Test
    public void send() {
    }

    @Test
    public void sendMessage() {
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_10000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_20000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_30000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_50000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_70000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_85000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Ice_water_mixing_ratio_10000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190806\\json\\gfs.t12z.pgrb2.0p25.f048_Geopotential_height_20000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190806\\json\\gfs.t12z.pgrb2.0p25.f048_Geopotential_height_30000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190806\\json\\gfs.t12z.pgrb2.0p25.f048_Geopotential_height_50000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190806\\json\\gfs.t12z.pgrb2.0p25.f048_Temperature_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190806\\json\\gfs.t12z.pgrb2.0p25.f048_Geopotential_height_85000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190806\\json\\gfs.t12z.pgrb2.0p25.f048_Geopotential_height_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Temperature_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Temperature_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Temperature_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190806\\json\\gfs.t12z.pgrb2.0p25.f048_Temperature_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Temperature_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Relative_humidity_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Geopotential_height_10000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190905\\json\\gfs.t12z.pgrb2.1p00.f000_Total_precipitation_0.json"));

//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Visibility_0.json"));
        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190924\\json\\gfs.t12z.pgrb2.1p00.f021_Temperature_20000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190911\\json\\gfs.t12z.pgrb2.1p00.f024_Total_cloud_cover_100000.json"));
//        jsonMsgProducer.sendMessage(new JsonQueueFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\20190805\\json\\gfs.t12z.pgrb2.1p00.f024_Vertical_velocity_100000.json"));
    }
}