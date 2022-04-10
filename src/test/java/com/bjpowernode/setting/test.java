package com.bjpowernode.setting;

import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.MD5Util;


public class test {

    public static void main(String[] args) {
        //验证失效时间
        String expireTime="2022-10-11 10:10:10";
        String newTime=DateTimeUtil.getSysTime();

        int count=expireTime.compareTo(newTime);

        System.out.println(count);

        String pwd="xpy";
        String p= MD5Util.getMD5(pwd);
        System.out.println(p);
        String pwds="xpy";
        String p1= MD5Util.getMD5(pwds);
        System.out.println(p1);
    }

}
