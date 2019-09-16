package com.zgss.grib.gribdata.util;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.zgss.grib.gribdata.entity.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherFactory {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Component_of_wind buildComponent_of_wind(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value) {
        Component_of_wind wind = new Component_of_wind();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Temperature buildTemperature(Date refTime,
                                        int surface1Value,
                                        double lon,
                                        double lat,
                                        double value) {
        Temperature wind = new Temperature();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Geopotential_height buildGeopotential_height(Date refTime,
                                                int surface1Value,
                                                double lon,
                                                double lat,
                                                double value) {
        Geopotential_height wind = new Geopotential_height();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Ice_water_mixing_ratio buildIce_water_mixing_ratio(Date refTime,
                                                   int surface1Value,
                                                   double lon,
                                                   double lat,
                                                   double value) {
        Ice_water_mixing_ratio wind = new Ice_water_mixing_ratio();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Total_precipitation buildTotal_precipitation(Date refTime,
                                               int surface1Value,
                                               double lon,
                                               double lat,
                                               double value) {
        Total_precipitation wind = new Total_precipitation();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Relative_humidity buildRelative_humidity(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value) {
        Relative_humidity wind = new Relative_humidity();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Total_cloud_cover buildTotal_cloud_cover(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value) {
        Total_cloud_cover wind = new Total_cloud_cover();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Visibility buildVisibility(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value) {
        Visibility wind = new Visibility();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };
    public static Vertical_velocity buildVertical_velocity(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value) {
        Vertical_velocity wind = new Vertical_velocity();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };

    public static Wind_speed_gust buildWind_speed_gust(Date refTime,
                                              int surface1Value,
                                              double lon,
                                              double lat,
                                              double value) {
        Wind_speed_gust wind = new Wind_speed_gust();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        return wind;
    };

    public static Weather buildWeather(Date refTime,
                                                       int surface1Value,
                                                       double lon,
                                                       double lat,
                                                       double value) {
        Point point = new Point(new Position(lon,lat));
        Weather wind = new Weather();
        wind.setReftime(refTime);
        wind.setSurfacevalue(surface1Value);
        wind.setValue(value);
        wind.setLon(lon);
        wind.setLat(lat);
        wind.setLocation(new GeoJsonPoint(lon, lat));
        return wind;
    };
}
