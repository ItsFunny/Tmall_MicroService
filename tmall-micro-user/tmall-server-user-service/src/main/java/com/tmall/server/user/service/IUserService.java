/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午6:54:49
* 
*/
package com.tmall.server.user.service;

import java.sql.SQLException;

import com.tmall.common.dto.UserDTO;
import com.tmall.server.user.common.model.TmallUser;

/**
* 
* @author joker 
* @date 创建时间：2018年6月13日 下午6:54:49
*/
public interface IUserService
{
	void updateUser(UserDTO userDTO) throws SQLException;
	
	boolean updateUserPassword(String newPassword,String oldPassword,Long userId);
	
	@Deprecated
	UserDTO findById(Long userId);
	
	TmallUser findUserByUserId(Long userId);
}
