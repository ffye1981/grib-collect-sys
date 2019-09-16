package com.zgss.grib.gribjson.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("schemes")
public class Schemes {

    public List<Scheme> getSchemeList() {
        return schemeList;
    }

    public void setSchemeList(List<Scheme> schemeList) {
        this.schemeList = schemeList;
    }

    @XStreamImplicit(itemFieldName="scheme")
    private List<Scheme> schemeList;
}
