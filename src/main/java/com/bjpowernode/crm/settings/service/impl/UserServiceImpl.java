package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;


public class UserServiceImpl implements UserService {

    private UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);


    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String ,Object> map=new HashMap<String, Object>();
        map.put("loginAct",loginAct);
        System.out.println("验证界面的用户名:loginAct---------->"+loginAct);
        map.put("loginPwd",loginPwd);
        System.out.println("验证界面的密码:loginPwd---------->"+loginPwd);

        User user=userDao.login(map);

        System.out.println("查询到的数据User:------------>"+user);

        //用户密码验证
        if(user==null){
            System.out.println("密码或用户名错误");
            throw new LoginException("密码或用户名错误");
        }

        //登录时间信息验证
        String expirtTime=user.getExpireTime();
        //获取系统的当前时间
        String currentTime= DateTimeUtil.getSysTime();
        if(expirtTime.compareTo(currentTime)<0){
            throw  new LoginException("登录信息已过期");
        }

        //登录账户是否上锁
        String lockState=user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("用户已经上锁，请联系管理员解锁");
        }

        //登录ip地址访问
        String allowIps=user.getAllowIps();
        if(!allowIps.contains(ip)){
            throw  new LoginException("ip归属地不存在");
        }
        return user;
    }
}
