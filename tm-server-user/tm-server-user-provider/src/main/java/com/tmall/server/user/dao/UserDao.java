/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午5:59:01
* 
*/
package com.tmall.server.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tmall.common.dto.UserDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月13日 下午5:59:01
*/
@Mapper
public interface UserDao
{
	void updateUserInfo(UserDTO userDTO);
	
	@Select("select * from tmall_user#{tableNum} where user_id=#{userId}")
	UserDTO findById(@Param("tableNum")Integer tableNum,@Param("userId")Long userId);
	
	@Update("update tmall_user#{tableNum} set password=#{newPassword} where password=#{oldPassword} and user_id=#{userId}")
	int updateUserPassword(@Param("tableNum")Integer tableNum,@Param("newPassword")String newPassword,@Param("oldPassword")String oldPassword,@Param("userId")Long userId);
	
}
