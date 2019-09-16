package com.zgss.grib.contour.entity;

import java.util.Date;

public class TemperatureKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Temperature.refTime
     *
     * @mbggenerated
     */
    private Date reftime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Temperature.surfaceValue
     *
     * @mbggenerated
     */
    private Integer surfacevalue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Temperature.refTime
     *
     * @return the value of Temperature.refTime
     *
     * @mbggenerated
     */
    public Date getReftime() {
        return reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Temperature.refTime
     *
     * @param reftime the value for Temperature.refTime
     *
     * @mbggenerated
     */
    public void setReftime(Date reftime) {
        this.reftime = reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Temperature.surfaceValue
     *
     * @return the value of Temperature.surfaceValue
     *
     * @mbggenerated
     */
    public Integer getSurfacevalue() {
        return surfacevalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Temperature.surfaceValue
     *
     * @param surfacevalue the value for Temperature.surfaceValue
     *
     * @mbggenerated
     */
    public void setSurfacevalue(Integer surfacevalue) {
        this.surfacevalue = surfacevalue;
    }
}