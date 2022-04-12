package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.domain.Activtiy;
import com.bjpowernode.crm.workbench.service.ActivityServie;

public class ActivityServiceImpl implements ActivityServie {

    private ActivityDao activityDao= SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    public boolean save(Activtiy a) {
        boolean flag=true;
        int count=activityDao.save(a);
        System.out.println("返回的数值count========="+count);
        if(count!=1){
            flag=false;
        }
        return flag;
    }
}
