package com.zgss.grib.contour.entity;

import java.io.Serializable;

public class GeoJson implements Serializable {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Object coordinates) {
        this.coordinates = coordinates;
    }

    private String type;
    private Object coordinates;
}
