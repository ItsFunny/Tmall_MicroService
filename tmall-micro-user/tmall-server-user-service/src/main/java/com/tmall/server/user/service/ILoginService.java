/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午9:45:24
* 
*/
package com.tmall.server.user.service;

import com.tmall.server.user.common.model.TmallEmailLogin;
import com.tmall.server.user.common.model.TmallMobileLogin;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月29日 下午9:45:24
*/
public interface ILoginService
{	
	TmallEmailLogin loginByEmail(String email);
	
	TmallMobileLogin loginByMobile(String mobile);
}
