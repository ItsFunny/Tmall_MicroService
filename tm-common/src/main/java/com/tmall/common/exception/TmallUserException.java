/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午9:32:30
* 
*/
package com.tmall.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* 
* @author joker 
* @date 创建时间：2018年8月18日 下午9:32:30
*/
@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
public class TmallUserException extends TmallBussinessException
{
	public TmallUserException(String message)
	{
		super(TmallBussinessException.UNAUTHENTICATED_EXCEPTION, message);
	}

	public TmallUserException(int code, String message)
	{
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月18日 下午9:33:50
	*/
	private static final long serialVersionUID = -5744886035159116167L;

}
