package com.zgss.grib.gribservice.entity;

public class Grib {
    public GribHeader getHeader() {
        return header;
    }

    public void setHeader(GribHeader header) {
        this.header = header;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }

    private GribHeader header;
    private Double[] data;
}
