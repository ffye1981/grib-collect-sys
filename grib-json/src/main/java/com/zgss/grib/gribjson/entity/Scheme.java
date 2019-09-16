package com.zgss.grib.gribjson.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("scheme")
public class Scheme {

    @XStreamAsAttribute
    @XStreamAlias("subject")
    private String subject;

    @XStreamAsAttribute
    @XStreamAlias("fc")
    private String fc;

    @XStreamAsAttribute
    @XStreamAlias("fp")
    private String fp;

    @XStreamAsAttribute
    @XStreamAlias("fs")
    private String fs;

    @XStreamAsAttribute
    @XStreamAlias("fv")
    private String fv;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public String getFv() {
        return fv;
    }

    public void setFv(String fv) {
        this.fv = fv;
    }
}
