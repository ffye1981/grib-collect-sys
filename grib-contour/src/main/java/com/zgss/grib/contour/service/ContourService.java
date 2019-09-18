package com.zgss.grib.contour.service;

import com.alibaba.fastjson.JSONObject;
import com.vividsolutions.jts.geom.Geometry;
import com.zgss.grib.common.util.DateUtil;
import com.zgss.grib.contour.entity.*;
import com.zgss.grib.contour.service.Impl.*;
import com.zgss.grib.contour.util.GisUtil;
import com.zgss.grib.contour.util.WeatherFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wContour.Contour;
import wContour.Global.Border;
import wContour.Global.PointD;
import wContour.Global.PolyLine;
import wContour.Global.Polygon;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.*;

/**
 * @program: grib-collect-sys
 * @description: 格点等值面服务
 * @author: ffye
 * @create: 2019-08-28 14:00
 */

@Service
public class ContourService {
    private Logger logger = LoggerFactory.getLogger(ContourService.class);

    @Autowired
    ClipPolygonService clipPolygonService;

    @Autowired
    Component_of_windService componentOfWindService;

    @Autowired
    Geopotential_heightService geopotentialHeightService;

    @Autowired
    Ice_water_mixing_ratioService iceWaterMixingRatioService;

    @Autowired
    Total_precipitationService totalPrecipitationService;

    @Autowired
    Relative_humidityService relativeHumidityService;

    @Autowired
    TemperatureService temperatureService;

    @Autowired
    Total_cloud_coverService totalCloudCoverService;

    @Autowired
    Vertical_velocityService verticalVelocityService;

    @Autowired
    VisibilityService visibilityService;

    @Autowired
    Wind_speed_gustService windSpeedGustService;

    @Autowired
    LegendService legendService;

//    private List<LegendItem> legends;

    public void calEquiSurface2(String parameterNumberName, Date refTime,
                                int surface1Value, double[][] gridData, double[] lons, double[] lats,
                                double min,double max) {
        double _undefData = -9999.0;
        SimpleFeatureCollection polygonCollection = null;
        List<PolyLine> cPolylineList = new ArrayList<PolyLine>();
        List<Polygon> cPolygonList = new ArrayList<Polygon>();
//        double[] _CValues = new double[]{
//                253.15,263.15,273.15,283.15,293.15,303.15,313.15,999
//        };
        try {
            long start = Calendar.getInstance().getTimeInMillis();
            double[] _CValues = null;
            String _parameterNumberName = parameterNumberName.equals("Geopotential_height")? parameterNumberName + surface1Value:parameterNumberName;
            if(parameterNumberName.equals("Geopotential_height")) {
                _CValues = this.getGeopotential_heightBreaks(surface1Value,min,max);
            }else {
                _CValues = this.legendService.listBreaks(_parameterNumberName);
            }
//
//            double[] _CValues = new double[]{
//                253.15,263.15,273.15,283.15,293.15,303.15,313.15,999
//        };

            if(_CValues == null || _CValues.length == 0) {
                return;
            }
            int nc = _CValues.length;
            int[][] S1 = new int[gridData.length][gridData[0].length];
            List<Border> _borders = Contour.tracingBorders(gridData, lons, lats,
                    S1, _undefData);
            cPolylineList = Contour.tracingContourLines(gridData, lons, lats, nc,
                    _CValues, _undefData, _borders, S1);// 生成等值线
            cPolylineList = Contour.smoothLines(cPolylineList);// 平滑
            if(parameterNumberName.equals("Geopotential_height")) {
                //等值线的计算
                List<Map> weathers = buildWeatherPolyLine(cPolylineList);
//                String data = buildWeatherPolyLine(parameterNumberName,refTime,surface1Value,cPolylineList);
//                logger.info(data);
                long computer_cost = (Calendar.getInstance().getTimeInMillis() - start);
                start = Calendar.getInstance().getTimeInMillis();
                this.saveContour(parameterNumberName,refTime,surface1Value,weathers);
//                this.saveContour2(parameterNumberName,refTime,surface1Value,data);
                long save_cost = (Calendar.getInstance().getTimeInMillis() - start);
//                data = null;
                weathers = null;
                logger.info("------" + parameterNumberName + "_" + surface1Value +  ",computer_cost:"+ computer_cost +",save_cost:" + save_cost + " millseconds。");
            }else {
                //等值面的计算
//                cPolygonList = Contour.tracingPolygons(gridData, cPolylineList,
//                        _borders, _CValues);
//                List<Map> weathers = buildWeatherPolygon(cPolygonList);
//                long computer_cost = (Calendar.getInstance().getTimeInMillis() - start);
//                start = Calendar.getInstance().getTimeInMillis();
//                this.saveContour(parameterNumberName,refTime,surface1Value,weathers);
//                long save_cost = (Calendar.getInstance().getTimeInMillis() - start);
                cPolygonList = Contour.tracingPolygons(gridData, cPolylineList,
                        _borders, _CValues);
                String data = buildWeatherPolygon(parameterNumberName,refTime,surface1Value,cPolygonList);
                long computer_cost = (Calendar.getInstance().getTimeInMillis() - start);
                start = Calendar.getInstance().getTimeInMillis();
                this.saveContour2(parameterNumberName,refTime,surface1Value,data);
                long save_cost = (Calendar.getInstance().getTimeInMillis() - start);
                data = null;
                cPolygonList = null;
                logger.info("------" + parameterNumberName  + "_" + surface1Value + ",computer_cost:"+ computer_cost +",save_cost:" + save_cost + " millseconds。");
            }
            _borders = null;
            cPolylineList = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Map> buildWeatherPolygon(List<Polygon> cPolygonList){
        List<Map> weathers = new ArrayList<Map>();
        try {
            for (Polygon pPolygon : cPolygonList) {
                Map _map = new HashMap();
                List<Object> ptsTotal = new ArrayList<Object>();
                List<Object> pts = new ArrayList<Object>();
                PolyLine pline = pPolygon.OutLine;
                for (PointD ptD : pline.PointList) {
                    List<Double> pt = new ArrayList<Double>();
                    pt.add(ptD.X);
                    pt.add(ptD.Y);
                    pts.add(pt);
                }
                ptsTotal.add(pts);

                if (pPolygon.HasHoles()) {
                    for (PolyLine cptLine : pPolygon.HoleLines) {
                        List<Object> cpts = new ArrayList<Object>();
                        for (PointD ccptD : cptLine.PointList) {
                            List<Double> pt = new ArrayList<Double>();
                            pt.add(ccptD.X);
                            pt.add(ccptD.Y);
                            cpts.add(pt);
                        }
                        if (cpts.size() > 0) {
                            ptsTotal.add(cpts);
                        }
                    }
                }

                JSONObject js = new JSONObject();
                js.put("type", "Polygon");
                js.put("coordinates", ptsTotal);
                _map.put("geometry",js.toString());
                double hv = pPolygon.HighValue;
                double lv = pPolygon.LowValue;

//                if (hv == lv) {
//                    if (pPolygon.IsClockWise) {
//                        if (!pPolygon.IsHighCenter) {
//                            hv = hv - 0.1;
//                            lv = lv - 0.1;
//                        }
//
//                    } else {
//                        if (!pPolygon.IsHighCenter) {
//                            hv = hv - 0.1;
//                            lv = lv - 0.1;
//                        }
//                    }
//                } else {
//                    if (!pPolygon.IsClockWise) {
//                        lv = lv + 0.1;
//                    } else {
//                        if (pPolygon.IsHighCenter) {
//                            hv = hv - 0.1;
//                        }
//                    }
//                }
                _map.put("hv",hv);
                _map.put("lv",lv);
//                System.out.println("hv1:" + hv + ",lv1:" + lv);
                weathers.add(_map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return weathers;
        }
        return weathers;
    }
    public static List<Map> buildWeatherPolyLine(List<PolyLine> cPolylineList){
        List<Map> weathers = new ArrayList<Map>();
        try {
            for (PolyLine pPolyline : cPolylineList) {
                Map _map = new HashMap();
                List<Object> ptsTotal = new ArrayList<Object>();
                for (PointD ptD : pPolyline.PointList) {
                    List<Double> pt = new ArrayList<Double>();
                    pt.add(ptD.X);
                    pt.add(ptD.Y);
                    ptsTotal.add(pt);
                }
                JSONObject js = new JSONObject();
                js.put("type", "LineString");
                js.put("coordinates", ptsTotal);
                _map.put("geometry",js.toString());
                double v = pPolyline.Value;
                _map.put("hv",v);
                weathers.add(_map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return weathers;
        }
        return weathers;
    }
    public String buildWeatherPolygon(String parameterNumberName, Date refTime,
                                                 int surface1Value,List<Polygon> cPolygonList){
        StringBuffer data = new StringBuffer();
        try {
            for (Polygon pPolygon : cPolygonList) {
                StringBuffer _data = new StringBuffer(DateUtil.getDateStr(refTime,0) + "\t");
                _data.append("0\t0\t");
                _data.append(surface1Value + "\t");
                _data.append("SRID=4326;");
                StringBuffer _wkt = new StringBuffer("POLYGON(");
                PolyLine pline = pPolygon.OutLine;
                _wkt.append(getPolygonWkt(pline.PointList));
                if (pPolygon.HasHoles()) {
                    for (PolyLine cptLine : pPolygon.HoleLines) {
                        _wkt.append(getPolygonWkt(cptLine.PointList));
                    }
                }
                //取消最后一个逗号
                _wkt = new StringBuffer(_wkt.substring(0,_wkt.length() - 1));
                _wkt.append(")");
//                Geometry _wktGeo = GisUtil.wkt2Geo(_wkt.toString());
//                Geometry _clipGeo = clipPolygonService.getGeometry().intersection(_wktGeo);
//                _wkt = new StringBuffer(GisUtil.geo2Wkt(_clipGeo));
                _data.append(_wkt.toString());
                _data.append("\t");
                double hv = pPolygon.HighValue;
                double lv = pPolygon.LowValue;
                _data.append(lv + System.getProperty("line.separator"));
                data.append(_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return data.toString();
        }
        return data.toString();
    }
    public static String buildWeatherPolyLine(String parameterNumberName, Date refTime,
                                             int surface1Value,List<PolyLine> cPolylineList){
        StringBuffer data = new StringBuffer();
        try {
            for (PolyLine pPolyline : cPolylineList) {
                StringBuffer _data = new StringBuffer(DateUtil.getDateStr(refTime,0) + "\t");
                _data.append("0\t0\t");
                _data.append(surface1Value + "\t");
                _data.append("SRID=4326;LINESTRING");
                _data.append(getPolygonWkt(pPolyline.PointList));
                //取消最后一个逗号
                _data = new StringBuffer(_data.substring(0,_data.length() - 1));
                System.out.println(_data.toString());
                _data.append("\t");
                double lv = pPolyline.Value;
                _data.append(lv + System.getProperty("line.separator"));
                data.append(_data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return data.toString();
        }
        return data.toString();
    }
    public static String getPolygonWkt(List<PointD> pList) {
        StringBuffer _wkt = new StringBuffer();

        if(pList!=null && pList.size()> 0) {
            _wkt.append("(");
            for (PointD ccptD : pList) {
                _wkt.append(ccptD.X + " " + ccptD.Y + ",");
            }
            PointD  p0 = pList.get(0);
            PointD  p1 = pList.get(pList.size()-1);
            if(p0.X!=p1.X || p0.Y!=p1.Y) {
                _wkt.append(p0.X + " " + p0.Y);
            }else {
                _wkt = new StringBuffer(_wkt.substring(0,_wkt.length() - 1));
            }
            _wkt.append("),");
        }

        return _wkt.toString();
    }

    /**
     * 保存矢量数据
     * @param parameterNumberName
     * @param weathers
     */
    private void saveContour(String parameterNumberName,Date refTime,
                             int surface1Value,List<Map> weathers) {
        List records;
        switch (parameterNumberName) {
            case "U-component_of_wind":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildComponent_of_wind(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                Component_of_windKey component_of_windKey = new Component_of_windKey();
                component_of_windKey.setReftime(refTime);
                component_of_windKey.setSurfacevalue(surface1Value);
                this.componentOfWindService.deleteByPrimaryKey(component_of_windKey);
                this.componentOfWindService.insertBatch(records);
                break;
            case "Temperature":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildTemperature(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                TemperatureKey TemperatureKey = new TemperatureKey();
                TemperatureKey.setReftime(refTime);
                TemperatureKey.setSurfacevalue(surface1Value);
                this.temperatureService.deleteByPrimaryKey(TemperatureKey);
                this.temperatureService.insertBatch(records);
                break;
            case "Geopotential_height":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildGeopotential_height(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                Geopotential_heightKey geopotential_heightKey = new Geopotential_heightKey();
                geopotential_heightKey.setReftime(refTime);
                geopotential_heightKey.setSurfacevalue(surface1Value);
                this.geopotentialHeightService.deleteByPrimaryKey(geopotential_heightKey);
                this.geopotentialHeightService.insertBatch(records);
                break;
            case "Ice_water_mixing_ratio":

                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildIce_water_mixing_ratio(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }

                Ice_water_mixing_ratioKey ice_water_mixing_ratioKey = new Ice_water_mixing_ratioKey();
                ice_water_mixing_ratioKey.setReftime(refTime);
                ice_water_mixing_ratioKey.setSurfacevalue(surface1Value);
                this.iceWaterMixingRatioService.deleteByPrimaryKey(ice_water_mixing_ratioKey);
                this.iceWaterMixingRatioService.insertBatch(records);
                break;
            case "Total_precipitation":  //Total_precipitation
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildTotal_precipitation(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                Total_precipitationKey total_precipitationKey = new Total_precipitationKey();
                total_precipitationKey.setReftime(refTime);
                total_precipitationKey.setSurfacevalue(surface1Value);
                this.totalPrecipitationService.deleteByPrimaryKey(total_precipitationKey);
                this.totalPrecipitationService.insertBatch(records);
                break;
            case "Relative_humidity":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildRelative_humidity(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                Relative_humidityKey relative_humidityKey = new Relative_humidityKey();
                relative_humidityKey.setReftime(refTime);
                relative_humidityKey.setSurfacevalue(surface1Value);
                this.relativeHumidityService.deleteByPrimaryKey(relative_humidityKey);
                this.relativeHumidityService.insertBatch(records);
                break;
            case "Total_cloud_cover":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildTotal_cloud_cover(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                Total_cloud_coverKey total_cloud_coverKey = new Total_cloud_coverKey();
                total_cloud_coverKey.setReftime(refTime);
                total_cloud_coverKey.setSurfacevalue(surface1Value);
                this.totalCloudCoverService.deleteByPrimaryKey(total_cloud_coverKey);
                this.totalCloudCoverService.insertBatch(records);
                break;
            case "Visibility":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildVisibility(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                VisibilityKey visibilityKey = new VisibilityKey();
                visibilityKey.setReftime(refTime);
                visibilityKey.setSurfacevalue(surface1Value);
                this.visibilityService.deleteByPrimaryKey(visibilityKey);
                this.visibilityService.insertBatch(records);
                break;
            case "Vertical_velocity":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildVertical_velocity(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                Vertical_velocityKey vertical_velocityKey = new Vertical_velocityKey();
                vertical_velocityKey.setReftime(refTime);
                vertical_velocityKey.setSurfacevalue(surface1Value);
                this.verticalVelocityService.deleteByPrimaryKey(vertical_velocityKey);
                this.verticalVelocityService.insertBatch(records);
                break;
            case "Wind_speed_gust":
                records = new ArrayList<>();
                for (Map _map: weathers) {
                    String geometry = _map.get("geometry").toString();
                    double hv = (double)_map.get("hv");
                    records.add(WeatherFactory.buildWind_speed_gust(refTime,
                            surface1Value, 0, 0, hv, geometry));
                }
                Wind_speed_gustKey wind_speed_gustKey = new Wind_speed_gustKey();
                wind_speed_gustKey.setReftime(refTime);
                wind_speed_gustKey.setSurfacevalue(surface1Value);
                this.windSpeedGustService.deleteByPrimaryKey(wind_speed_gustKey);
                this.windSpeedGustService.insertBatch(records);
                break;
            default:
                ;
        }
    }
    private void saveContour2(String parameterNumberName,Date refTime,
                             int surface1Value,String data) {
        switch (parameterNumberName) {
            case "U-component_of_wind":
                if(this.componentOfWindService.existsTable(refTime,surface1Value,true)) {
                    Component_of_windKey component_of_windKey = new Component_of_windKey();
                    component_of_windKey.setReftime(refTime);
                    component_of_windKey.setSurfacevalue(surface1Value);
                    this.componentOfWindService.deleteByPrimaryKey(component_of_windKey);
                }
                this.componentOfWindService.copyIn(refTime,surface1Value,data);
                break;
            case "Temperature":
                if(this.temperatureService.existsTable(refTime,surface1Value,true)) {
                    TemperatureKey TemperatureKey = new TemperatureKey();
                    TemperatureKey.setReftime(refTime);
                    TemperatureKey.setSurfacevalue(surface1Value);
                    this.temperatureService.deleteByPrimaryKey(TemperatureKey);
                }
                this.temperatureService.copyIn(refTime,surface1Value,data);
                break;
            case "Geopotential_height":
                if(this.geopotentialHeightService.existsTable(refTime,surface1Value,true)) {
                    Geopotential_heightKey geopotential_heightKey = new Geopotential_heightKey();
                    geopotential_heightKey.setReftime(refTime);
                    geopotential_heightKey.setSurfacevalue(surface1Value);
                    this.geopotentialHeightService.deleteByPrimaryKey(geopotential_heightKey);
                }
                this.geopotentialHeightService.copyIn(refTime,surface1Value,data);
                break;
            case "Ice_water_mixing_ratio":
                if(this.iceWaterMixingRatioService.existsTable(refTime,surface1Value,true)) {
                    Ice_water_mixing_ratioKey ice_water_mixing_ratioKey = new Ice_water_mixing_ratioKey();
                    ice_water_mixing_ratioKey.setReftime(refTime);
                    ice_water_mixing_ratioKey.setSurfacevalue(surface1Value);
                    this.iceWaterMixingRatioService.deleteByPrimaryKey(ice_water_mixing_ratioKey);
                }
                this.iceWaterMixingRatioService.copyIn(refTime,surface1Value,data);
                break;
            case "Total_precipitation":  //Total_precipitation
                if(this.totalPrecipitationService.existsTable(refTime,surface1Value,true)) {
                    Total_precipitationKey total_precipitationKey = new Total_precipitationKey();
                    total_precipitationKey.setReftime(refTime);
                    total_precipitationKey.setSurfacevalue(surface1Value);
                    this.totalPrecipitationService.deleteByPrimaryKey(total_precipitationKey);
                }
                this.totalPrecipitationService.copyIn(refTime,surface1Value,data);
                break;
            case "Relative_humidity":
                if(this.relativeHumidityService.existsTable(refTime,surface1Value,true)) {
                    Relative_humidityKey relative_humidityKey = new Relative_humidityKey();
                    relative_humidityKey.setReftime(refTime);
                    relative_humidityKey.setSurfacevalue(surface1Value);
                    this.relativeHumidityService.deleteByPrimaryKey(relative_humidityKey);
                }
                this.relativeHumidityService.copyIn(refTime,surface1Value,data);
                break;
            case "Total_cloud_cover":
                if(this.totalCloudCoverService.existsTable(refTime,surface1Value,true)) {
                    Total_cloud_coverKey total_cloud_coverKey = new Total_cloud_coverKey();
                    total_cloud_coverKey.setReftime(refTime);
                    total_cloud_coverKey.setSurfacevalue(surface1Value);
                    this.totalCloudCoverService.deleteByPrimaryKey(total_cloud_coverKey);
                }
                this.totalCloudCoverService.copyIn(refTime,surface1Value,data);
                break;
            case "Visibility":
                if(this.visibilityService.existsTable(refTime,surface1Value,true)) {
                    VisibilityKey visibilityKey = new VisibilityKey();
                    visibilityKey.setReftime(refTime);
                    visibilityKey.setSurfacevalue(surface1Value);
                    this.visibilityService.deleteByPrimaryKey(visibilityKey);
                }
                this.visibilityService.copyIn(refTime,surface1Value,data);
                break;
            case "Vertical_velocity":
                if(this.verticalVelocityService.existsTable(refTime,surface1Value,true)) {
                    Vertical_velocityKey vertical_velocityKey = new Vertical_velocityKey();
                    vertical_velocityKey.setReftime(refTime);
                    vertical_velocityKey.setSurfacevalue(surface1Value);
                    this.verticalVelocityService.deleteByPrimaryKey(vertical_velocityKey);
                }
                this.verticalVelocityService.copyIn(refTime,surface1Value,data);
                break;
            case "Wind_speed_gust":
                if(this.windSpeedGustService.existsTable(refTime,surface1Value,true)) {
                    Wind_speed_gustKey wind_speed_gustKey = new Wind_speed_gustKey();
                    wind_speed_gustKey.setReftime(refTime);
                    wind_speed_gustKey.setSurfacevalue(surface1Value);
                    this.windSpeedGustService.deleteByPrimaryKey(wind_speed_gustKey);
                }
                this.windSpeedGustService.copyIn(refTime,surface1Value,data);
                break;
            default:
                ;
        }
    }

    /**
     * 位势高度断点计算
     * @param surface1Value
     * @param max
     * @param min
     * @return
     */
    public double[] getGeopotential_heightBreaks(int surface1Value,double min,double max) {
        List<Double> _lbreaks = null;
        int _step = 40;
        double _splitValue = 5880;
        double _min = Math.min(max,min);
        double _max = Math.max(max,min);
        if(_max == _min) {
            _lbreaks = new ArrayList<Double>();
            _lbreaks.add(_max);
        }else if(_max - _min <= _step) {
            _lbreaks = new ArrayList<Double>();
            _lbreaks.add(_min);
            if(surface1Value == 50000 && (_min < _splitValue && _splitValue < _max)) {
                _lbreaks.add(_splitValue);
            }
            _lbreaks.add(_max);
        }else {
            if(surface1Value == 50000) {
                if(_max <= _splitValue || _min >=_splitValue ) {
                    _lbreaks = this.getGeopotential_heightBreaks(_min,_max,_step);
                }else {
                    _lbreaks = this.getGeopotential_heightBreaks(_min,_splitValue,_step);
                    _lbreaks.remove(_lbreaks.size()-1);
                    List<Double> _lbreaksPost = this.getGeopotential_heightBreaks(_splitValue,_max,_step);
                    _lbreaks.addAll(_lbreaksPost);
                }
            }else {

                _lbreaks = this.getGeopotential_heightBreaks(_min,_max,_step);
            }

        }
        double[] _breaks = new double[_lbreaks.size()];
        for (int i=0;i<_breaks.length;i++) {
            _breaks[i] = _lbreaks.get(i).doubleValue();
        }
        return _breaks;
    }

    /**
     * 位势高度断点计算
     * @param _step
     * @param max
     * @param min
     * @return
     */
    public List<Double> getGeopotential_heightBreaks(double min,double max,int _step){
        List<Double> _lbreaks = new ArrayList<Double>();
        double value = min;
        while(value <= max){
            _lbreaks.add(value);
            if(value < max && (value + _step) > max) {
                _lbreaks.add(max);
            }
            value = value + _step;
        }
        return _lbreaks;
    }
}
