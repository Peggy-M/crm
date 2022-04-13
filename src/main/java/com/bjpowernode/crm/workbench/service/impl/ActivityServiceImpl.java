package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.domain.Activtiy;
import com.bjpowernode.crm.workbench.service.ActivityServie;
import com.sun.glass.ui.android.Activity;

import java.util.List;
import java.util.Map;

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


    public PaginationVO<Activity> pageList(Map<String, Object> map) {
        //取得total
        int total=activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> dataList=activityDao.getActivityListByCondition(map);

        //创建一个vo对象，将total和dataList封装到vo对象中
        PaginationVO vo=new PaginationVO();
        vo.setTotal(total);
        vo.setDataLsit(dataList);

        return vo;
    }
}
