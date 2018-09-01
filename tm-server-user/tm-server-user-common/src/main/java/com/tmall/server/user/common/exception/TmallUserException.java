/**
*
* @author joker 
* @date 创建时间：2018年9月1日 上午10:40:35
* 
*/
package com.tmall.server.user.common.exception;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
* 
* @author joker 
* @date 创建时间：2018年9月1日 上午10:40:35
*/
public class TmallUserException extends TmallBussinessException
{

	public TmallUserException(ErrorCodeEnum enum1)
	{
		super(enum1);
	}

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月1日 上午10:40:44
	*/
	private static final long serialVersionUID = -2184136067200669156L;
	
}
