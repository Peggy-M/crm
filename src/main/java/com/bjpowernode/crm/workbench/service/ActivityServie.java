package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.Activtiy;
import com.sun.glass.ui.android.Activity;

import java.util.Map;

public interface ActivityServie {

    boolean save(Activtiy ac);

    PaginationVO<Activity> pageList(Map<String, Object> map);
}
