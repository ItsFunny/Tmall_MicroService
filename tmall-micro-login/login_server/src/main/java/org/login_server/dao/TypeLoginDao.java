/**
*
* @author joker 
* @date 创建时间：2018年5月14日 下午1:21:40
* 
*/
package org.login_server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* 
* @author joker 
* @date 创建时间：2018年5月14日 下午1:21:40
*/
@Mapper
public interface TypeLoginDao
{
	@Select("select user_id from user_email_login where email=#{email}")
	String findIdByEmail(String email);
	
	@Select("select user_id from user_openid_login where openid=#{openid} ")
	String findIdByOpenid(String openid);
	
}
