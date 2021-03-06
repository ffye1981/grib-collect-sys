package com.zgss.grib.gribdata.base;

import com.zgss.grib.gribdata.entity.Component_of_wind;
import com.zgss.grib.gribdata.entity.Component_of_windKey;

import java.util.List;

public interface BaseMapper<T,PK> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Component_of_wind
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(PK key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Component_of_wind
     *
     * @mbggenerated
     */
    int insert(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Component_of_wind
     *
     * @mbggenerated
     */
    int insertSelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Component_of_wind
     *
     * @mbggenerated
     */
    T selectByPrimaryKey(PK key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Component_of_wind
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Component_of_wind
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(T record);

    int countByPrimaryKey(PK key);

    int insertBatch(List<T> dataList);
}
