package com.zgss.grib.entity;

import java.io.Serializable;

public class JsonQueueFile implements Serializable {
    private String fileName;

    public JsonQueueFile(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
