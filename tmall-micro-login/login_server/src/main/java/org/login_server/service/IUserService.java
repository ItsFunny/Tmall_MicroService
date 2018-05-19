/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午9:49:43
* 
*/
package org.login_server.service;

import com.tmall.common.model.User;

/**
* 
* @author joker 
* @date 创建时间：2018年5月14日 上午9:49:43
*/
public interface IUserService
{
	User findByEmail(String email);
	
	void updateUserLastLoginTimeAndIp(Long userId,String lastIp);
}
