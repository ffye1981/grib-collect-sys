package com.zgss.grib.contour.service;

import com.alibaba.fastjson.JSONObject;
import com.zgss.grib.contour.entity.Temperature;
import com.zgss.grib.contour.service.Impl.TemperatureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContourServiceTest {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Autowired
    ContourService contourService;

    @Autowired
    TemperatureService temperatureService;

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void getGeopotential_heightBreaks() {
        double[] breaks = this.contourService.getGeopotential_heightBreaks(20000,1d,1d);
        for (double b: breaks) {
            System.out.println(b);
        }
        System.out.println("====================================================================================");
        breaks = this.contourService.getGeopotential_heightBreaks(20000,1d,10d);
        for (double b: breaks) {
            System.out.println(b);
        }
        System.out.println("====================================================================================");
        breaks = this.contourService.getGeopotential_heightBreaks(20000,10d,42d);
        for (double b: breaks) {
            System.out.println(b);
        }
        System.out.println("====================================================================================");
        breaks = this.contourService.getGeopotential_heightBreaks(50000,10d,42d);
        for (double b: breaks) {
            System.out.println(b);
        }
        System.out.println("====================================================================================");
        breaks = this.contourService.getGeopotential_heightBreaks(50000,20d,42d);
        for (double b: breaks) {
            System.out.println(b);
        }
        System.out.println("====================================================================================");
        breaks = this.contourService.getGeopotential_heightBreaks(50000,5800d,5880d);
        for (double b: breaks) {
            System.out.println(b);
        }
        System.out.println("====================================================================================");
        breaks = this.contourService.getGeopotential_heightBreaks(50000,5800d,5920d);
        for (double b: breaks) {
            System.out.println(b);
        }
        System.out.println("====================================================================================");
        breaks = this.contourService.getGeopotential_heightBreaks(50000,5880d,5921d);
        for (double b: breaks) {
            System.out.println(b);
        }
    }

    public void compare() {
        List<Temperature> dataList = new ArrayList<Temperature>();
        for (int i=0;i<6000;i++){
            Temperature wind = new Temperature();
            wind.setLon(new Double(0));
            wind.setLat(new Double(89.50));
            wind.setValue(new Double(i));
            try {
                wind.setReftime(sdf.parse("2019-07-03 11:00:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            wind.setSurfacevalue(new Integer(20000));
//            wind.setGeojson();
            dataList.add(wind);
        }
        this.temperatureService.insertBatch(dataList);
    }

    @Test
    public void testConn() throws SQLException, IOException {
        String filePath = "E://Total_cloud_cover_2019_08_06.txt";
        String tableName = "\"Total_cloud_cover\"";
        BaseConnection conn = sqlSessionTemplate.getConnection().unwrap(BaseConnection.class);
//        BaseConnection connection = (BaseConnection) SqlSessionUtils.getSqlSession(
//                sqlSessionTemplate.getSqlSessionFactory(), sqlSessionTemplate.getExecutorType(),
//                sqlSessionTemplate.getPersistenceExceptionTranslator()).getConnection();
//        FileInputStream fileInputStream = null;
//        try {
//            CopyManager copyManager = new CopyManager(conn);
//            fileInputStream = new FileInputStream(filePath);
//            copyManager.copyIn("COPY " + tableName + " FROM STDIN", fileInputStream);
//        } finally {
//            if (fileInputStream != null) {
//                try {
//                    fileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        try {
            // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            StringBuilder stringBuilder = new StringBuilder();
            // 读入TXT文件
            // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            String pathname = "E://Legend.txt";
            // 要读取以上路径的input。txt文件
            File filename = new File(pathname);
            // 建立一个输入流对象reader
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            // 建立一个对象
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                // 一次读入一行数据
                line = br.readLine();
                stringBuilder.append(line);
            }
            br.close();
            reader.close();
            System.out.println(stringBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}