package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Activtiy;
import com.sun.glass.ui.android.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {

    int save(Activtiy ac);

    List<Activity> getActivityListByCondition(Map<String, Object> map);

    int getTotalByCondition(Map<String, Object> map);
}
