package com.zgss.grib.gribdata.entity;

import java.util.Date;

public class VisibilityKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Visibility.refTime
     *
     * @mbggenerated
     */
    private Date reftime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Visibility.surfaceValue
     *
     * @mbggenerated
     */
    private Integer surfacevalue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Visibility.refTime
     *
     * @return the value of Visibility.refTime
     *
     * @mbggenerated
     */
    public Date getReftime() {
        return reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Visibility.refTime
     *
     * @param reftime the value for Visibility.refTime
     *
     * @mbggenerated
     */
    public void setReftime(Date reftime) {
        this.reftime = reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Visibility.surfaceValue
     *
     * @return the value of Visibility.surfaceValue
     *
     * @mbggenerated
     */
    public Integer getSurfacevalue() {
        return surfacevalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Visibility.surfaceValue
     *
     * @param surfacevalue the value for Visibility.surfaceValue
     *
     * @mbggenerated
     */
    public void setSurfacevalue(Integer surfacevalue) {
        this.surfacevalue = surfacevalue;
    }
}