package com.zgss.grib.contour.util;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GisUtil {
    /**  The GeometryFactory. */
    private static GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory( null );

    /** The wkt writer. */
    private static WKTWriter wktWriter=new WKTWriter();

    /** The wkt reader. */
    private static WKTReader wktReader=new WKTReader(JTSFactoryFinder.getGeometryFactory( null ));

    /**
     * 读取
     *
     * @param wkt WKT格式数据
     * @return the geometry JTS空间对象
     */
    public static Geometry wkt2Geo(String wkt)
    {
        try {
            return wktReader.read(wkt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入
     *
     * @param geometry JTS空间对象
     * @return the string WKT格式数据
     */
    public static String geo2Wkt(Geometry geometry)
    {
        return  wktWriter.write(geometry);
    }

    /**
     * create multiLine
     * @return
     */
    public static MultiLineString createMLine(LineString[] lineStrings){
        MultiLineString ms = geometryFactory.createMultiLineString(lineStrings);
        return ms;
    }

    /**
     * create multiLine
     * @return
     */
    public static GeometryCollection createGeoCollection(Geometry[] geos){
        GeometryCollection geoCollection = new GeometryCollection(geos,geometryFactory);
        return geoCollection;
    }

    /**
     * 长度转角度
     * @param meters
     * @return
     */
    public static double meterToRadian(double meters){
        double radian=meters*(360/(2* Math.PI * 6378137.0));
        return radian;
    }

    /**
     * 生成缓冲区
     * @param geo
     * @param distanceMeter
     * @return
     */
    public static Geometry bufferGeo(Geometry geo,double distanceMeter){
        double radian=meterToRadian(distanceMeter);
        return geo.buffer(radian);
    }

    /**
     * 生成缓冲区
     * @param geoWkt
     * @param distanceMeter
     * @return
     */
    public static Geometry bufferGeo(String geoWkt, double distanceMeter){
        Geometry bufferGeometry = null;
        if(geoWkt!=null && !geoWkt.isEmpty()){
            WKTReader reader=new WKTReader();
            try {
                Geometry geometry=reader.read(geoWkt);
                bufferGeometry= bufferGeo(geometry,distanceMeter);
            } catch (ParseException e) {

            }
        }
        return bufferGeometry;
    }

    /**
     * 生成缓冲区
     * @param geoCollection
     * @param distanceMeter
     * @return
     */
    public static Geometry bufferGeo(GeometryCollection geoCollection, double distanceMeter){
        double radian=meterToRadian(distanceMeter);
        return geoCollection.buffer(radian);
    }

    /**
     * 创建点
     * @param lat 经度
     * @param lat 维度
     * @return
     */
    public static Point createPoint(double lon,double lat){
        return geometryFactory.createPoint(new Coordinate(lon,lat));
    }

    public static String wktToGeojson(String wkt) {
        String json = null;
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            StringWriter writer = new StringWriter();
            GeometryJSON g = new GeometryJSON();
            g.write(wkt2Geo(wkt), writer);
            map.put("$geometry", writer);
            json = JSONObject.toJSONString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static MultiPolygon polygonToMultiPolygon(Polygon[] polygons){
        return geometryFactory.createMultiPolygon(polygons);
    }


    public static void main(String[] args){
        String wkt = wktToGeojson("POLYGON((107.60009765625001 35.272979736328125,108.82781982421876 35.492706298828125,110.15991210937501 35.371856689453125,110.36315917968751 34.177093505859375,110.22033691406251 33.476715087890625,109.06402587890626 33.251495361328125,108.01208496093751 33.347625732421875,107.25128173828126 34.037017822265625,107.44079589843751 34.940643310546875,107.60009765625001 35.272979736328125))");
        System.out.println(wkt);
    }
}
