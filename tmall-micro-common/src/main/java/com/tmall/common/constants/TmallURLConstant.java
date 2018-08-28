/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午2:51:45
* 
*/
package com.tmall.common.constants;

/**
* 
* 存放本系统的所有url链接
* @author joker 
* @date 创建时间：2018年5月17日 下午2:51:45
*/
public interface TmallURLConstant
{
	String TMALL_LOGIN_SSO_CHECKLOGIN_URL="http://login.com:8000/checkLogin";
	String TMALL_LOGIN_SSO_AUTH_TOKEN_URL="http://login.com:8000/authToken";
	
	//这个是需要感知的,不能这么写
	String TMALL_AUTH_URL="http://localhost:9000/auth";  //身份验证以及用户鉴权
	
	//
	
	String TMALL_LOGIN_URL="http://localhost:9000/login";
	
	String TMALL_REGISTER_URL="";
	String TMALL_PORTAL_INDEX_URL="http://localhost:9001/index";
	
	
	
	
	
	String TMALL_MANAGEMENT_SYSTEM="http://localhost:9001";
	
}
