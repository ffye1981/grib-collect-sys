package com.zgss.grib.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class GribUtil {
    private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    static {
        //用格林威治时间生成表名
        sdf.setCalendar(new GregorianCalendar(
                new SimpleTimeZone(0, "GMT")));
    }

    public static String getGribName(String parameterNumberName,
                                     Date refTime,
                                     int surface1Value) {
        StringBuffer collentBuffer = new StringBuffer("Grib_");
        collentBuffer.append(parameterNumberName + "_");
        collentBuffer.append(sdf.format(refTime));
        return collentBuffer.toString();
    }

    public static String getGridName(String parameterNumberName,
                                     Date refTime,
                                     int surface1Value) {
        StringBuffer collentBuffer = new StringBuffer("Grid_");
        collentBuffer.append(parameterNumberName + "_");
        collentBuffer.append(sdf.format(refTime));
        return collentBuffer.toString();
    }

    public static String getContourName(String parameterNumberName,
                                     Date refTime,
                                     int surface1Value) {
        StringBuffer collentBuffer = new StringBuffer(parameterNumberName + "_");
        collentBuffer.append(sdf.format(refTime));
        return collentBuffer.toString();
    }
}
