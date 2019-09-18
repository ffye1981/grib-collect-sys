package com.zgss.grib.contour.base;

import java.util.Date;
import java.util.List;

public interface IBaseService<T,PK> {
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

    PK getKey(T record);
    boolean exists(PK key);
    void upSert(T record);
    void upSert(List<T> records);
    int insertBatch(List<T> records);
    String getTable(Date refTime, int surface1Value);
    boolean existsTable(Date refTime, int surface1Value,boolean create);
    void copyIn(Date refTime, int surface1Value, String streamData);
    List<String> getSubTables();
    void removeSubTable(String tableName);
    void clearSubTables(String tableName);
}
