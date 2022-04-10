package com.bjpowernode.crm.web.filter;

import com.bjpowernode.crm.settings.domain.User;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入用户的登录过滤界面");

        //由于这里的需要获取Sessios域对象中的user
        //Session获取，请求转发，通过ServletRequest的子接口实现
        HttpServletResponse response= (HttpServletResponse) res;
        HttpServletRequest request= (HttpServletRequest) req;

        //获取当前页面的路径进登录页进行判断，如果是登录的界面 则放行
        //负责就会死循环
        String  path=request.getServletPath();
        if("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
            chain.doFilter(req,res);
        }else {
            //其他的资源必须进行验证
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                //如果不等于空 放行
                chain.doFilter(req, res);
            } else {
                //重定义到登录的界面
                /*
                 * 这里之所以在没有使用请求转发:请求转发会导致页面的Url不会刷新为当前页面的地址 我们希望的是在进行登录的时候我们的url是当前页面的地址
                 * 转发:
                 * /login.jsp
                 *  使用的是一种特殊格式的路径 这里的路径只需要 / 开头 直接跟随 资源路径即可 这样的路径被称为内部路径
                 *重定义：
                 * 使用的是传统路径 前面必须 /项目名开头 ，后面跟具体的资源路径
                 *
                 * /crm/login.jsp
                 *   {PageContext.request.getContextPath}
                 *   request.getContextPath() 获取的是项目名
                 * */
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }
}
