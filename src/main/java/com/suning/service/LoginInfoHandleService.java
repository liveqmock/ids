package com.suning.service;

import com.suning.dto.LoginInfoBean;

/**
 * 
 * 
 * 功能描述： passport业务处理
 * @author 14042369
 * @created 2014-7-15 下午3:47:31
 * @version 1.0.0
 * @date 2014-7-15 下午3:47:31
 */
public interface LoginInfoHandleService {
    /**
     * 
     * 功能描述：根据username 判断是否插入还是更新数据
     */
    public void addOrUpdateLoginInfo(LoginInfoBean loginInfoBean);
    
    /**
     * 
     * 功能描述：根据用户名查询已登录的userid
     */
    public String getUseridByUsername(String username);
    
    /**
     * 
     * 功能描述：根据initService 截取service 并且拼接token
     */
    public String getService(String initService,String token);
    /**
     * 
     * 功能描述：根据suerid生成token
     * @param userid
     * @return token
     */
    public String getTokenByUserid(String userid);
    /**
     * 
     * 功能描述：登录  修改用户status状态为0
     * @param username  用户名(必须)
     * @param password 用户密码(非必须)
     * @return     如果service为空或者根据username未查询到记录 返回 "forward:/serviceIsNull?token="+token
     *             否则返回 "redirect:" + resultServive
     */
    public String login(LoginInfoBean loginInfoBean);
    /**
     * 
     * 功能描述：登出  修改用户status状态  1
     * @param token 
     * @return 返回值
     */
    public String logout(String token);

}
