package com.bjpowernode.crm.workbench.web.controller;


import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入市场活动的控制器");
        //获取的是一个的路径
        String path=request.getServletPath();

        if("/workbench/activity/gitUserList.do".equals(path)){
            gitUserList(request,response);
        }else if("/settings/user/xxx.do".equals(path)){
            //xxx(request,response)
        }
    }

    private void gitUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");

         UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());

         List<User> uList= us.getUserList();


        PrintJson.printJsonObj(response,uList);
    }


}
