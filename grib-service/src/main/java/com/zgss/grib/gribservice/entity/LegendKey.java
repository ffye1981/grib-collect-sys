package com.zgss.grib.gribservice.entity;

public class LegendKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Legend.parameterNumberName
     *
     * @mbggenerated
     */
    private String parameternumbername;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Legend.order
     *
     * @mbggenerated
     */
    private Integer order;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Legend.parameterNumberName
     *
     * @return the value of Legend.parameterNumberName
     *
     * @mbggenerated
     */
    public String getParameternumbername() {
        return parameternumbername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Legend.parameterNumberName
     *
     * @param parameternumbername the value for Legend.parameterNumberName
     *
     * @mbggenerated
     */
    public void setParameternumbername(String parameternumbername) {
        this.parameternumbername = parameternumbername == null ? null : parameternumbername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Legend.order
     *
     * @return the value of Legend.order
     *
     * @mbggenerated
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Legend.order
     *
     * @param order the value for Legend.order
     *
     * @mbggenerated
     */
    public void setOrder(Integer order) {
        this.order = order;
    }
}