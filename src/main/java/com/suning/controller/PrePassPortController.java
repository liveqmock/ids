package com.suning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suning.constant.IdsConstant;
import com.suning.dto.LoginInfoBean;
import com.suning.service.LoginInfoHandleService;
import com.suning.service.LoginInfoService;
@Controller
public class PrePassPortController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PrePassPortController.class);
    
    @Autowired
    LoginInfoService loginInfoService;
    @Autowired
    LoginInfoHandleService loginInfoHandleService;
    /**
     *  
     * 功能描述：
     * @param 参数说明
     * 返回值:  类型 <说明> 
     * @return 返回值 
     */
    @RequestMapping(value={"/login"})
    public String login( @RequestParam String username, String service,String password) {
        LoginInfoBean loginInfoBean=new LoginInfoBean(username, password, null, service);
        return loginInfoHandleService.login(loginInfoBean);
       
    }
    /**
     * 访问/login时如果参数service为空则跳转此url
     * 功能描述：
     * 输入参数：<按照参数定义顺序> 
     * @param 参数说明
     * 返回值:  类型 <说明> 
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    @RequestMapping(value={"/serviceIsNull"})
    @ResponseBody
    public String serviceIsNull(@RequestParam String token){
        return token;
    }

    /**
     * 
     * 功能描述：根据token查询出登录的userId
     * 输入参数：<按照参数定义顺序> 
     * @param 参数说明
     * 返回值:  类型 <说明> 
     * @return 返回值
     */
    @RequestMapping("/queryUserIdByToken")
    @ResponseBody
    public String queryUserIdByToken(@RequestParam String token) {
        return loginInfoService.queryUserIdByToken(IdsConstant.STATUS_1,token);
    }
    
    /**
     * 
     * 功能描述：根据token查询出登录的username
     * 输入参数：<按照参数定义顺序> 
     * @param 参数说明
     * 返回值:  类型 <说明> 
     * @return 返回值
     */
    @RequestMapping("/queryUsernameByToken")
    @ResponseBody
    public String queryUsernameByToken(@RequestParam String token){
        return loginInfoService.queryUsernamedByToken(IdsConstant.STATUS_1,token);
       
    }

    /**
     * 
     * 功能描述：登出的 
     * 输入参数：<按照参数定义顺序> 
     * @param 参数说明
     * 返回值:  类型 <说明> 
     * @return 返回值
     */
    @RequestMapping(value={"/logout"},produces={"application/json; charset=utf-8"})
    @ResponseBody
    public String logout(@RequestParam String token) {
        return loginInfoHandleService.logout(token);
    }
    
    @RequestMapping("/addLoginInfo")
    @ResponseBody
    public void addLoginInfo(@RequestParam String username ,@RequestParam String userid,String password){
        LoginInfoBean loginInfoBean=new LoginInfoBean(username, password, userid, null);
        loginInfoHandleService.addOrUpdateLoginInfo(loginInfoBean);
    }
    
    /**
     * 
     * 功能描述：打印异常
     * 输入参数：<按照参数定义顺序> 
     * @param 参数说明
     * 返回值:  类型 <说明> 
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    @ExceptionHandler(Exception.class)  
    public void handleException(Exception e){
        LOGGER.info("",e);
    }

}
