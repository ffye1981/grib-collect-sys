package com.zgss.grib.gribdata.entity;

public class Temperature extends TemperatureKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Temperature.lon
     *
     * @mbggenerated
     */
    private Double lon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Temperature.lat
     *
     * @mbggenerated
     */
    private Double lat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Temperature.value
     *
     * @mbggenerated
     */
    private Double value;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Temperature.lon
     *
     * @return the value of Temperature.lon
     *
     * @mbggenerated
     */
    public Double getLon() {
        return lon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Temperature.lon
     *
     * @param lon the value for Temperature.lon
     *
     * @mbggenerated
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Temperature.lat
     *
     * @return the value of Temperature.lat
     *
     * @mbggenerated
     */
    public Double getLat() {
        return lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Temperature.lat
     *
     * @param lat the value for Temperature.lat
     *
     * @mbggenerated
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Temperature.value
     *
     * @return the value of Temperature.value
     *
     * @mbggenerated
     */
    public Double getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Temperature.value
     *
     * @param value the value for Temperature.value
     *
     * @mbggenerated
     */
    public void setValue(Double value) {
        this.value = value;
    }
}