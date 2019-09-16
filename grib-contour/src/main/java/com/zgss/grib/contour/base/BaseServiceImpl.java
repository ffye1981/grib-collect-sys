package com.zgss.grib.contour.base;

import com.zgss.grib.common.util.GribUtil;
import com.zgss.grib.contour.util.ListUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public abstract class BaseServiceImpl<T,PK> implements IBaseService<T,PK> {

    private BaseMapper<T,PK> mapper;

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    protected void setBaseMapper(BaseMapper<T,PK> mapper) {
        this.mapper = mapper;
    }

    @Override
    public int deleteByPrimaryKey(PK key) {
        return this.mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(T record) {
        return this.mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return this.mapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(PK key) {
        return this.mapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return this.mapper.updateByPrimaryKey(record);
    }

    @Override
    public abstract PK getKey(T record);


    @Override
    public boolean exists(PK key) {
        return this.mapper.countByPrimaryKey(key) > 0;
    }

    @Override
    public void upSert(T record) {
        if(this.exists(this.getKey(record))) {
            this.updateByPrimaryKey(record);
        }else {
            this.insert(record);
        }
    }

    @Override
    public void upSert(List<T> records) {
        for (T record: records) {
            this.upSert(record);
        }
    }
    @Override
    public int insertBatch(List<T> records) {
        int cnt = 0;
        List<List<T>> groups = ListUtil.splitList(records,5000);
        for (List<T> list: groups) {
            cnt = cnt + this.mapper.insertBatch(list);
        }
        return cnt;
    }

    public void copyIn(Date refTime, int surface1Value,String streamData){
        InputStream fileInputStream = null;
        try {
            String tableName = this.getTable(refTime,surface1Value);
            BaseConnection conn = sqlSessionTemplate.getConnection().unwrap(BaseConnection.class);
            CopyManager copyManager = new CopyManager(conn);
            fileInputStream = new ByteArrayInputStream(streamData.getBytes());
            System.out.println("COPY \"" + tableName + "\" FROM STDIN");
            copyManager.copyIn("COPY \"" + tableName + "\" FROM STDIN", fileInputStream);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected String getBaseTable(){
        return null;
    }

    public String getTable(Date refTime, int surface1Value){
        return GribUtil.getContourName(this.getBaseTable(),refTime,surface1Value);
    }

    public boolean existsTable(Date refTime, int surface1Value,boolean create){
        String tableName = this.getTable(refTime,surface1Value);
        boolean exists = this.mapper.existsTable(tableName) > 0;
        if(create && !exists) {
            this.mapper.createTable(tableName);
        }
        return exists;
    }

    public List<String> getSubTables(){
        String preFix = this.getBaseTable();
        return this.mapper.selectSubTables(preFix + "_");
    }

    public void removeSubTable(String tableName){
        this.mapper.dropTable(tableName);
    }
}
