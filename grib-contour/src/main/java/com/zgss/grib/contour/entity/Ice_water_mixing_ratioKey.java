package com.zgss.grib.contour.entity;

import java.util.Date;

public class Ice_water_mixing_ratioKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Ice_water_mixing_ratio.refTime
     *
     * @mbggenerated
     */
    private Date reftime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Ice_water_mixing_ratio.surfaceValue
     *
     * @mbggenerated
     */
    private Integer surfacevalue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Ice_water_mixing_ratio.refTime
     *
     * @return the value of Ice_water_mixing_ratio.refTime
     *
     * @mbggenerated
     */
    public Date getReftime() {
        return reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Ice_water_mixing_ratio.refTime
     *
     * @param reftime the value for Ice_water_mixing_ratio.refTime
     *
     * @mbggenerated
     */
    public void setReftime(Date reftime) {
        this.reftime = reftime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Ice_water_mixing_ratio.surfaceValue
     *
     * @return the value of Ice_water_mixing_ratio.surfaceValue
     *
     * @mbggenerated
     */
    public Integer getSurfacevalue() {
        return surfacevalue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Ice_water_mixing_ratio.surfaceValue
     *
     * @param surfacevalue the value for Ice_water_mixing_ratio.surfaceValue
     *
     * @mbggenerated
     */
    public void setSurfacevalue(Integer surfacevalue) {
        this.surfacevalue = surfacevalue;
    }
}