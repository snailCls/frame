package com.zzh.logic.common.service;

import com.zzh.logic.utils.dto.Dto;
import com.zzh.logic.utils.page.Page;

import java.util.List;

public interface BaseService<T,C> {

    /**
     * 根据id获得对象
     * @param id
     * @return
     */
    T getByIdObj(C id);


    /**
     * 根据column查询，返回对象
     * @param dto
     * @return
     */
    T getByColumn(Dto dto);


    /**
     * 通过id删除
     * @param id
     * @return
     */
    int delObjById(C id);

    /**
     * 通过column删除
     * @param dto
     * @return
     */
    int deleteByColumn(Dto dto);

    /**
     * 保存不为空字段
     * @param t
     * @return
     */
    int insertObj(T t);

    /**
     * 批量保存不为空字段
     * @param list
     * @return
     */
    int insertObjBatch( List<T> list);


    /**
     * 根据id, 更新不为空字段
     * @param t
     * @return
     */
    int updateObj(T t);




    /**
     * 查询所有,返回实体
     * @param dto 参数，非必填
     * @return List 实体
     */
    List<T>  findAllObj(Dto dto);


    /**
     * 根据条件获取条数
     * @param dto
     * @return
     */
    int queryForPageObjCount(Dto dto);


    /**
     * 分页查询对象列表
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<T> queryForPageObj(Dto dto, int pageNo, int pageSize) ;



    /**
     * 分页查询
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<T> queryForPageObjNoCount(Dto dto, int pageNo, int pageSize);


}
