package com.suning.dto;

public class LoginInfoBean {
    private String username;

    private String password;

    private String userid;
    
    private String service;
    
    /**
     * 1 登录 
     * 0 登出
     */
    private String status;
    /**
     * 根据username生成的token
     */
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    public LoginInfoBean(String username, String password, String userid, String service) {
        this.username = username;
        this.password = password;
        this.userid = userid;
        this.service = service;
    }

    public LoginInfoBean() {
    }

   

    

}
