package com.zgss.grib.gribdata.entity;

import java.util.Date;

public class Relative_humidityKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Relative_humidity.refTime
     *
     * @mbggenerated
     */
    private Date reftime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Relative_humidity.surfaceValue
     *
     * @mbggenerated
     */
    private Integer surfacevalue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Relative_humidity.refTime
     *
     * @return the value of Relative_humidity.refTime
     *
     * @mbggenerated
     */
    public Date getReftime() {
        return reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Relative_humidity.refTime
     *
     * @param reftime the value for Relative_humidity.refTime
     *
     * @mbggenerated
     */
    public void setReftime(Date reftime) {
        this.reftime = reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Relative_humidity.surfaceValue
     *
     * @return the value of Relative_humidity.surfaceValue
     *
     * @mbggenerated
     */
    public Integer getSurfacevalue() {
        return surfacevalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Relative_humidity.surfaceValue
     *
     * @param surfacevalue the value for Relative_humidity.surfaceValue
     *
     * @mbggenerated
     */
    public void setSurfacevalue(Integer surfacevalue) {
        this.surfacevalue = surfacevalue;
    }
}