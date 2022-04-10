package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入用户的控制器");
        //获取的是一个的路径
        String path=request.getServletPath();

        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }else if("/settings/user/xxx.do".equals(path)){
            //xxx(request,response)
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到用户的登录验证界面");

        //获取请求的数据
        String loginAct= request.getParameter("loginAct");
        String loginPwd= request.getParameter("loginPwd");
        //通过MD5将密码信息转化为密文
        loginPwd= MD5Util.getMD5(loginPwd);
        System.out.println("密码---------"+loginPwd);
        //获取登录页面的ip地址
        String ip=request.getRemoteAddr();
        System.out.println("ip---------->"+ip);

        //对于业务层的开发统一使用的是代理类的接口对象
        //张三转李四的代理对象
        UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{

            User user =us.login(loginAct,loginPwd,ip);

            //将获取的User对象放入到Sessios域中
            //如果程序执行到此处则表示没有异常,既就是没有登录验证的错误
            request.getSession().setAttribute("user",user);

            /*
            *{"success":true}
            *
            * */

            //利用jackson工具自动生成 json 字符串格式 {"success":true}
            //printJsonFlag() 单个信息调用此方法
            //登录成功的信息包装
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){

            //一旦执行到此处catch块的信息，说明业务层验证登录失败  抛出异常
            /*
             *{"success":true,"msg":?}
             *
             * */
            //获取异常的信息
            String msg=e.getMessage();
            System.out.println("异常信息----->"+msg);
            /*
            * 由于返回的多个信息
            *   则需要打包Map集合，或者创建一个Vo类
            *
            * */
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);

            //利用jackson工具自动生成 json 字符串格式 {"success":true}
            //printJsonObj() 多个信息调用此方法
            //登录失败的信息包装
            PrintJson.printJsonObj(response,map);
            e.printStackTrace();
        }
    }

}
