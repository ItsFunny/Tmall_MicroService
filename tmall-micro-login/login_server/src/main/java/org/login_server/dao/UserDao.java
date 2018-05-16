/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午8:43:36
* 
*/
package org.login_server.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.login_server.model.User;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 上午8:43:36
 */
@Mapper
public interface UserDao
{
	@Select("select user_id,username,email,password,ID_card,mobile,openid,status,last_login_ip from user#{tableNum} where email=#{email}")
	User findByEmail(@Param("tableNum") Integer tableNum, @Param("email") String email);
	
	@Update("update user#{tableNum} set last_login_date=#{date},last_login_ip=#{last_login_ip} where user_id=#{userId}")
	void updateLastLoginTimeAndIp(@Param("tableNum")Integer tableName,@Param("userId")Long userId,@Param("last_login_ip")String ip,@Param("date")Date date);
}
