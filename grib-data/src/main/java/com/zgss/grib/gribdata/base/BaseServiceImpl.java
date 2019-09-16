package com.zgss.grib.gribdata.base;

import com.zgss.grib.gribdata.util.ListUtil;

import java.util.List;

public abstract class BaseServiceImpl<T,PK> implements IBaseService<T,PK> {

    private BaseMapper<T,PK> mapper;

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
        List<List<T>> groups = ListUtil.splitList(records,6000);
        for (List<T> list: groups) {
            cnt = cnt + this.mapper.insertBatch(list);
        }
        return cnt;
    }
}
