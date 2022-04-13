package com.bjpowernode.crm.workbench.web.controller;


import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.Activtiy;
import com.bjpowernode.crm.workbench.service.ActivityServie;
import com.bjpowernode.crm.workbench.service.impl.ActivityServiceImpl;
import com.sun.glass.ui.android.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入市场活动的控制器");
        //获取的是一个的路径
        String path=request.getServletPath();

        if("/workbench/activity/gitUserList.do".equals(path)){
            gitUserList(request,response);
        }else if("/workbench/activity/saveUser.do".equals(path)){
            saveUser(request,response);
        }else if("/workbench/activity/pageList.do".equals(path)){
            pageListr(request,response);
        }
    }

    //用户信息插入
    private void saveUser(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入用户插入信息");

        //随机生成一个id
        String id= UUIDUtil.getUUID();
        String owner=request.getParameter("owner");
        String name=request.getParameter("name");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");
        String cost=request.getParameter("cost");
        String description=request.getParameter("description");
        //修改时间是当前的系统的时间
        String createTime= DateTimeUtil.getSysTime();
        //创建人当前登录用户
        String crearteBy= ((User) request.getSession().getAttribute("user")).getName();
        System.out.println("当前的登录用户=========="+crearteBy);


        Activtiy a= new Activtiy();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(crearteBy);
        System.out.println("插入的用户为===="+a);

        ActivityServie as= (ActivityServie) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag=as.save(a);


        PrintJson.printJsonFlag(response,flag);


    }
    //用户信息列表
    private void gitUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");

         UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());

         List<User> uList= us.getUserList();


        PrintJson.printJsonObj(response,uList);
    }
    //市场信息查询
    private void pageListr(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入市场用户信息查询（结合条件查询与分页查询）");

        String name=request.getParameter("name");
        String owner=request.getParameter("owner");
        String startDate=request.getParameter("startDate");
        String endDate=request.getParameter("endDate");

        String pageNoStr=request.getParameter("pageNo");
        int pageNo=Integer.valueOf(pageNoStr);
        //每页的记录数
        String pageSizeStr=request.getParameter("pageSize");
        int pageSize=Integer.valueOf(pageSizeStr);
        //计算略过的记录数
       int skipCount=(pageNo-1)*pageSize;

       Map<String ,Object> map=new HashMap<String,Object>();
       map.put("name",name);
       map.put("owner",owner);
       map.put("startDate",startDate);
       map.put("endDate",endDate);
       map.put("skipCount",skipCount);
       map.put("pageSize",pageSize);

       ActivityServie as= (ActivityServie) ServiceFactory.getService(new ActivityServiceImpl());

       /*
       * 前端：
       *    市场活动信息
       *    查询的总条数
       *
       *    业务层拿到以上两条信息后需要向前台响应
       *    map
       *    map.put("dataList:"datalist)
       *    map.put("total":total)
       *    PrintJSON map--> json
       *    {"total":100,"dataList":[{市场活动1}，{2}，{3}]}
       *
       * 这里返回一个的前端接受的数据
       *     Map集合 / Vo类
       * 由于这里的返回的数据的再前台会多次的大量调用  所以这里使用Vo类 更合适
       *
       *    vo
       *     PaginationVO<T>
       *        private int total;
       *        private List<T> dataLsit;
       *    PaginationVO<Activity> vo=new PaginationVO<>;
       *    vo.setTotal(total);
       *    vo.setDataList(dataList)
       *
       *    PrintJSON vo---> json
       *    {"total":100,"dataList":[{市场活动1}，{2}，{3}]}
       * */
       PaginationVO<Activity> vo= as.pageList(map);

       //vo---> {"total":100,"dataList":[{市场活动1}，{2}，{3}]}
       PrintJson.printJsonObj(response, vo);
    }

}
