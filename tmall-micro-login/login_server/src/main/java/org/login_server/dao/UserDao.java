/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午8:43:36
* 
*/
package org.login_server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.login_server.model.User;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 上午8:43:36
 */
@Mapper
public interface UserDao
{
	@Select("select user_id,username,email,password,ID_card,mobile,openid,status from user#{tableNum} where email=#{email}")
	User findByEmail(@Param("tableNum") Integer tableNum, @Param("email") String email);
}
