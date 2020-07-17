package com.zzh.logic.demo.dao;

import com.zzh.logic.common.dao.BaseMapperDao;
import com.zzh.logic.demo.entity.Demo;
import org.mapstruct.Mapper;

@Mapper
public interface DemoSysMapperDao extends BaseMapperDao<Demo,Long> {
}
