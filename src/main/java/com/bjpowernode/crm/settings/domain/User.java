package com.bjpowernode.crm.settings.domain;

/*
*   一般在市场中使用的日期格式
*           年   月    日
*           yyyy-MM-dd (10位字符串)
*           年   月    日   时   分   秒
*           yyyy-MM-dd HH:mm:ss   (19位字符串)
* */


/*
*   关于登陆的操作
*
*       验证账号与密码
*       User user = 执行sql语句 select * from tab_user where loginAct=? and loginPwd=?
*       当这里的User对象为null 则表示的登录是失败
*       当这里的User对象不为空 则表示账号与密码只是验证成功 但不是登录成功 要想登录成功还需要验证以下信息
*        1、exipreTime 验证登录失效时间
*        2、lockState 验证账号的当前的状态 是否是被锁定
*        3、allowIps  验证登陆的ip是否存在
*
*       只有以上几点同时满足，才表示登录成功
* */
public class User {

    private String id;    //用户的id
    private String loginAct;    //登录账号
    private String name;    //用户名
    private String loginPwd;    //登录密码
    private String email;    //用户邮箱
    private String expireTime;    //失效时间 19
    private String lockState;    //锁定状态 0锁定 1启动
    private String deptno;    //部门编号
    private String allowIps;    //允许访问的ip地址
    private String createTime;    //创建时间
    private String createBy;    //创建人
    private String editTime;    //修改时间
    private String editBy;    //修改人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginAct='" + loginAct + '\'' +
                ", name='" + name + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", email='" + email + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", lockState='" + lockState + '\'' +
                ", deptno='" + deptno + '\'' +
                ", allowIps='" + allowIps + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                '}';
    }
}
