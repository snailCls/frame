package com.zzh.logic.demo.service.impl;

import com.zzh.logic.common.dao.BaseMapperDao;
import com.zzh.logic.common.service.impl.BaseServiceImpl;
import com.zzh.logic.demo.dao.DemoMapperDao;
import com.zzh.logic.demo.dao.DemoSysMapperDao;
import com.zzh.logic.demo.entity.Demo;
import com.zzh.logic.demo.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("demoService")
public class DemoServiceImpl extends BaseServiceImpl<Demo, Long> implements DemoService {

    @Resource
    DemoSysMapperDao demoSysMapperDao;

    @Resource
    DemoMapperDao demoMapperDao;

    @Override
    protected BaseMapperDao<Demo, Long> getBasemapperDao() {
        return demoSysMapperDao;
    }
}
