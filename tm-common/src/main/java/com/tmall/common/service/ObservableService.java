/**
*
* @author joker 
* @date 创建时间：2018年8月29日 上午10:58:07
* 
*/
package com.tmall.common.service;

import com.tmall.common.dto.UserDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年8月29日 上午10:58:07
*/
public interface ObservableService
{
	void changedAndNotify(UserDTO user, String detail);
}
