package com.zgss.grib.contour.service;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @program: grib-collect-sys
 * @description: 裁剪服务：加载一个面对象，并用来裁剪等值面
 * @author: ffye
 * @create: 2019-09-18 10:00
 */
@Service
@ConfigurationProperties(prefix="clip")
public class ClipPolygonService {
    private Logger logger = LoggerFactory.getLogger(ClipPolygonService.class);
    private String shp;
    private Geometry geometry;

    @PostConstruct
    public void loadData(){
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        try {
            ShapefileDataStore dataStore = (ShapefileDataStore)dataStoreFactory.createDataStore(
                    new File(this.shp).toURI().toURL());
            dataStore.setCharset(Charset.forName("GBK"));
            SimpleFeatureSource featureSource = dataStore.getFeatureSource();
            SimpleFeatureCollection sfc = featureSource.getFeatures();
            SimpleFeatureIterator iterator = sfc.features();
            while (iterator.hasNext()) {
                SimpleFeature ft = iterator.next();
                this.geometry = (MultiPolygon)ft.getDefaultGeometry();
                break;
            }
            logger.info("load：" + this.shp + " success!");
        } catch (Exception e) {
            logger.info("load：" + this.shp + " failed!");
        }

    }
    public Geometry getGeometry() {
        return geometry;
    }
    public Envelope getEnvelope() {
        return geometry.getEnvelopeInternal();
    }

    public String getShp() {
        return shp;
    }

    public void setShp(String shp) {
        this.shp = shp;
    }

}
