package com.suning.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.constant.IdsConstant;
import com.suning.dto.LoginInfoBean;
import com.suning.framework.dal.client.DalClient;
import com.suning.service.LoginInfoService;
import com.suning.util.ObjectUtils;

@Service
public class LoginInfoServiceImpl implements LoginInfoService {
    @Autowired
    DalClient dalClient;

    @Override
    public LoginInfoBean getLoginInfoBeanByUsername(String username) {
        Map<String,Object> map=new HashMap<String ,Object>();
        map.put("username", username);
        LoginInfoBean loginInfoBean=dalClient.queryForObject("T_LOGIN_INFO.SELECT_BY_FIELDS",map , LoginInfoBean.class);
        return  loginInfoBean;
    }

    @Override
    public int insertLoginInfo(LoginInfoBean loginInfoBean) {
       return  dalClient.execute("T_LOGIN_INFO.INSERT", ObjectUtils.object2Map(loginInfoBean));
        
    } 

    @Override
    public int deleteLoginInfo(LoginInfoBean loginInfoBean) {
        return  dalClient.execute("T_LOGIN_INFO.DELETE", ObjectUtils.object2Map(loginInfoBean));
    }

    @Override
    public int updateLoginInfoByUsername(LoginInfoBean loginInfoBean) {
        return dalClient.execute("T_LOGIN_INFO.UPDATE",  ObjectUtils.object2Map(loginInfoBean));
    }

    @Override
    public LoginInfoBean getLoginInfoBeanByUserid(String userid) {
        Map<String,Object> map=new HashMap<String ,Object>();
        map.put("userid", userid);
        LoginInfoBean loginInfoBean=dalClient.queryForObject("T_LOGIN_INFO.SELECT_BY_FIELDS",map , LoginInfoBean.class);
        return  loginInfoBean;
    }

    @Override
    public int udpateStatusByUsername(String status,String username) {
        LoginInfoBean loginInfoBean=new LoginInfoBean();
        loginInfoBean.setStatus(status);
        loginInfoBean.setUsername(username);
        return  updateLoginInfoByUsername(loginInfoBean);
    }

    @Override
    public String queryUserIdByToken(String token) {
        LoginInfoBean loginInfoBean=queryLoginInfoBeanByToken(token);
        return loginInfoBean!=null?loginInfoBean.getUserid():null;
    }

    @Override
    public String queryUsernamedByToken(String token) {
        LoginInfoBean loginInfoBean=queryLoginInfoBeanByToken(token);
        return loginInfoBean!=null?loginInfoBean.getUsername():null;
    }

    @Override
    public LoginInfoBean queryLoginInfoBeanByToken(String token) {
        Map<String,Object> map=new HashMap<String ,Object>();
        map.put("token", token);
        map.put("status", IdsConstant.STATUS_1);
        return dalClient.queryForObject("T_LOGIN_INFO.SELECT_BY_FIELDS",map , LoginInfoBean.class);
    }

}
