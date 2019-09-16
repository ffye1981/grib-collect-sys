package com.zgss.girb.gribdownload.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("task")
public class Task {

    @XStreamAsAttribute
    @XStreamAlias("subject")
    private String subject;

    @XStreamAsAttribute
    @XStreamAlias("variable")
    private String variable;

    @XStreamAsAttribute
    @XStreamAlias("level")
    private String level;

    public Subregion getSubregion() {
        return subregion;
    }

    public void setSubregion(Subregion subregion) {
        this.subregion = subregion;
    }

    @XStreamAsAttribute
    @XStreamAlias("subregion")
    private Subregion subregion;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
