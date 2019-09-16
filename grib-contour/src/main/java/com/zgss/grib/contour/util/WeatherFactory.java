package com.zgss.grib.contour.util;

import com.zgss.grib.contour.entity.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherFactory {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Component_of_wind buildComponent_of_wind(Date refTime,
                                                           int surface1Value,
                                                           double lon,
                                                           double lat,
                                                           double value,
                                                           String geometry) {
        Component_of_wind wind = new Component_of_wind();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Temperature buildTemperature(Date refTime,
                                               int surface1Value,
                                               double lon,
                                               double lat,
                                               double value,
                                               String geometry) {
        Temperature wind = new Temperature();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Geopotential_height buildGeopotential_height(Date refTime,
                                                               int surface1Value,
                                                               double lon,
                                                               double lat,
                                                               double value,
                                                               String geometry) {
        Geopotential_height wind = new Geopotential_height();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        BigDecimal _value = new BigDecimal(value);
        _value = _value.divide(new BigDecimal(10),0, RoundingMode.DOWN);
        wind.setValue(_value.doubleValue());
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Ice_water_mixing_ratio buildIce_water_mixing_ratio(Date refTime,
                                                                     int surface1Value,
                                                                     double lon,
                                                                     double lat,
                                                                     double value,
                                                                     String geometry) {
        Ice_water_mixing_ratio wind = new Ice_water_mixing_ratio();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Total_precipitation buildTotal_precipitation(Date refTime,
                                                             int surface1Value,
                                                             double lon,
                                                             double lat,
                                                             double value,
                                                             String geometry) {
        Total_precipitation wind = new Total_precipitation();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Relative_humidity buildRelative_humidity(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value,
                                                           String geometry) {
        Relative_humidity wind = new Relative_humidity();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Total_cloud_cover buildTotal_cloud_cover(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value,
                                                           String geometry) {
        Total_cloud_cover wind = new Total_cloud_cover();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Visibility buildVisibility(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value,
                                             String geometry) {
        Visibility wind = new Visibility();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
    public static Vertical_velocity buildVertical_velocity(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value,
                                                           String geometry) {
        Vertical_velocity wind = new Vertical_velocity();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };

    public static Wind_speed_gust buildWind_speed_gust(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value,
                                                       String geometry) {
        Wind_speed_gust wind = new Wind_speed_gust();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setGeojson(geometry);
        return wind;
    };
}
