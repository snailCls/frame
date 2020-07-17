package com.zzh.logic.common.service.impl;

import com.zzh.logic.common.dao.BaseMapperDao;
import com.zzh.logic.common.service.BaseService;
import com.zzh.logic.utils.dto.Dto;
import com.zzh.logic.utils.page.Page;

import java.util.List;

public abstract class BaseServiceImpl<T,C>  implements BaseService<T,C> {

    /**
     * 功能：获取BasemapperDao对象
     * @return BasemapperDao实例
     * 继承可用
     */
    protected abstract BaseMapperDao<T,C> getBasemapperDao();

    @Override
    public T getByIdObj(C id) {
        return getBasemapperDao().getByIdObj(id);
    }

    @Override
    public T getByColumn(Dto dto) {
        return getBasemapperDao().getByColumn(dto);
    }

    @Override
    public int delObjById(C id) {
        return getBasemapperDao().delObjById(id);
    }

    @Override
    public int deleteByColumn(Dto dto) {
        return getBasemapperDao().deleteByColumn(dto);
    }

    @Override
    public int insertObj(T t) {
        return getBasemapperDao().insertObj(t);
    }

    @Override
    public int insertObjBatch(List<T> list) {
        return 0;
    }

    @Override
    public int updateObj(T t) {
        return 0;
    }

    @Override
    public List<T> findAllObj(Dto dto) {
        return null;
    }

    @Override
    public int queryForPageObjCount(Dto dto) {
        return 0;
    }

    @Override
    public Page<T> queryForPageObj(Dto dto, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<T> queryForPageObjNoCount(Dto dto, int pageNo, int pageSize) {
        return null;
    }
}
