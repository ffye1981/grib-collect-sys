package com.zgss.girb.gribdownload.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("subregion")
public class Subregion {

    @XStreamAsAttribute
    @XStreamAlias("leftlon")
    private String leftlon;

    @XStreamAsAttribute
    @XStreamAlias("rightlon")
    private String rightlon;

    @XStreamAsAttribute
    @XStreamAlias("toplat")
    private String toplat;

    @XStreamAsAttribute
    @XStreamAlias("bottomlat")
    private String bottomlat;

    public String getLeftlon() {
        return leftlon;
    }

    public void setLeftlon(String leftlon) {
        this.leftlon = leftlon;
    }

    public String getRightlon() {
        return rightlon;
    }

    public void setRightlon(String rightlon) {
        this.rightlon = rightlon;
    }

    public String getToplat() {
        return toplat;
    }

    public void setToplat(String toplat) {
        this.toplat = toplat;
    }

    public String getBottomlat() {
        return bottomlat;
    }

    public void setBottomlat(String bottomlat) {
        this.bottomlat = bottomlat;
    }
}
