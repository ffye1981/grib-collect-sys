package com.zgss.grib.contour.entity;

public class Ice_water_mixing_ratio extends Ice_water_mixing_ratioKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Ice_water_mixing_ratio.lon
     *
     * @mbggenerated
     */
    private Double lon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Ice_water_mixing_ratio.lat
     *
     * @mbggenerated
     */
    private Double lat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Ice_water_mixing_ratio.value
     *
     * @mbggenerated
     */
    private Double value;

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    private String geojson;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Ice_water_mixing_ratio.lon
     *
     * @return the value of Ice_water_mixing_ratio.lon
     *
     * @mbggenerated
     */
    public Double getLon() {
        return lon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Ice_water_mixing_ratio.lon
     *
     * @param lon the value for Ice_water_mixing_ratio.lon
     *
     * @mbggenerated
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Ice_water_mixing_ratio.lat
     *
     * @return the value of Ice_water_mixing_ratio.lat
     *
     * @mbggenerated
     */
    public Double getLat() {
        return lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Ice_water_mixing_ratio.lat
     *
     * @param lat the value for Ice_water_mixing_ratio.lat
     *
     * @mbggenerated
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Ice_water_mixing_ratio.value
     *
     * @return the value of Ice_water_mixing_ratio.value
     *
     * @mbggenerated
     */
    public Double getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Ice_water_mixing_ratio.value
     *
     * @param value the value for Ice_water_mixing_ratio.value
     *
     * @mbggenerated
     */
    public void setValue(Double value) {
        this.value = value;
    }
}