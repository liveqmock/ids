package com.suning.service;

import com.suning.dto.LoginInfoBean;

public interface LoginInfoService {
    
    public LoginInfoBean getLoginInfoBeanByUsername(String status,String username);
        
    public int insertLoginInfo(LoginInfoBean loginInfoBean);
    
    public int deleteLoginInfo(LoginInfoBean loginInfoBean);
    
    public int updateLoginInfoByUsername(LoginInfoBean loginInfoBean);
    /**
     * 
     * 功能描述：根据用户名更新status
     * @param 参数说明
     * 返回值:  类型 <说明> 
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public int udpateStatusByUsername(String status ,String username);
    
    public LoginInfoBean getLoginInfoBeanByUserid(String status,String userid);

    /**
     * 
     * 功能描述：根据token获取userid
     */
    public String queryUserIdByToken(String status,String token);
    
    public String queryUsernamedByToken(String status,String token);
    
    public LoginInfoBean queryLoginInfoBeanByToken(String status,String token) ;
}
