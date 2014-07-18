package com.suning.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suning.constant.IdsConstant;
import com.suning.dto.LoginInfoBean;
import com.suning.framework.dal.transaction.annotation.Transactional;
import com.suning.service.LoginInfoHandleService;
import com.suning.service.LoginInfoService;

@Service
public class LoginInfoHandleServiceImpl implements LoginInfoHandleService {
    

    @Autowired
    LoginInfoService            loginInfoService;

    @Override
    @Transactional
    public void addOrUpdateLoginInfo(LoginInfoBean loginInfoBean) {
        LoginInfoBean bean = loginInfoService.getLoginInfoBeanByUsername(null,loginInfoBean.getUsername());
        loginInfoBean.setToken(getTokenByUserid(loginInfoBean.getUserid()));
        if (bean != null) {
            loginInfoService.updateLoginInfoByUsername(loginInfoBean);
        } else {
            loginInfoBean.setStatus(IdsConstant.STATUS_0);// 初始化status
            loginInfoService.insertLoginInfo(loginInfoBean);
        }

    }

    @Override
    public String getUseridByUsername(String username) {
        LoginInfoBean loginInfoBean = loginInfoService.getLoginInfoBeanByUsername(IdsConstant.STATUS_1,username);
        return loginInfoBean != null ? loginInfoBean.getUserid() : null;
    }

    @Override
    public String getService(String initService, String token) {
        String result = initService;
        String[] values = initService.split("targetUrl=");
        if (values.length == 2) {
            result = values[1];
        }
        if (result.contains("?")) {
            result += "&" + IdsConstant.TOKEN + "=" + token;
        } else {
            result += "?" + IdsConstant.TOKEN + "=" + token;
        }
        return result;
    }

    @Override
    public String getTokenByUserid(String userid) {
        return Base64.encodeBase64String(userid.getBytes());
    }

    @Override
    @Transactional
    public String login(LoginInfoBean loginInfoBean) {
        LoginInfoBean loginInfoBeanOfQuery = loginInfoService.getLoginInfoBeanByUsername(null,loginInfoBean.getUsername());
        String token="";
        if(loginInfoBeanOfQuery != null){
            loginInfoService.udpateStatusByUsername(IdsConstant.STATUS_1,loginInfoBeanOfQuery.getUsername());
            token=loginInfoBeanOfQuery.getToken();
        }
        if (StringUtils.isNotBlank(loginInfoBean.getService())&&loginInfoBeanOfQuery != null) {
            String resultServive = getService(loginInfoBean.getService(),loginInfoBeanOfQuery.getToken());
            return "redirect:" + resultServive;
        } else {
            return "forward:/serviceIsNull?token="+token;
        }
    }
    
    @Override
    public String logout(String token){
        LoginInfoBean loginInfoBean=loginInfoService.queryLoginInfoBeanByToken(IdsConstant.STATUS_1,token);
        if(loginInfoBean!=null){
            loginInfoService.udpateStatusByUsername(IdsConstant.STATUS_0, loginInfoBean.getUsername());
            return "{'success':"+true+"}";
        }else{
            return "{'success':"+false+"}";
        }
    }
    

}
