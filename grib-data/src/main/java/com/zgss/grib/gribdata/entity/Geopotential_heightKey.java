package com.zgss.grib.gribdata.entity;

import java.util.Date;

public class Geopotential_heightKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Geopotential_height.refTime
     *
     * @mbggenerated
     */
    private Date reftime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Geopotential_height.surfaceValue
     *
     * @mbggenerated
     */
    private Integer surfacevalue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Geopotential_height.refTime
     *
     * @return the value of Geopotential_height.refTime
     *
     * @mbggenerated
     */
    public Date getReftime() {
        return reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Geopotential_height.refTime
     *
     * @param reftime the value for Geopotential_height.refTime
     *
     * @mbggenerated
     */
    public void setReftime(Date reftime) {
        this.reftime = reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Geopotential_height.surfaceValue
     *
     * @return the value of Geopotential_height.surfaceValue
     *
     * @mbggenerated
     */
    public Integer getSurfacevalue() {
        return surfacevalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Geopotential_height.surfaceValue
     *
     * @param surfacevalue the value for Geopotential_height.surfaceValue
     *
     * @mbggenerated
     */
    public void setSurfacevalue(Integer surfacevalue) {
        this.surfacevalue = surfacevalue;
    }
}