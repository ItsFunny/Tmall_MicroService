/**
*
* @author joker 
* @date 创建时间：2018年5月14日 下午12:44:35
* 
*/
package com.tmall.common.constants;

/**
* 
* @author joker 
* @date 创建时间：2018年5月14日 下午12:44:35
*/
public interface RedisConstant
{
	String USER_INFO="user_info:%s";
	
	String USER_SERVLER_URL="USER_SERVLER_URL:%s";
	
	
	Integer USER_INFO_EXPIRE=7000;
	
	
	//用户在线的所有字系统编号
	String USER_LOGIN_SYSTEMS="USER_LOGIN_SYSTEMS:%s";
}
