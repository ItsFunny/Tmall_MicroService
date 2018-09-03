/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午9:32:30
* 
*/
package com.tmall.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tmall.common.enums.ErrorCodeEnum;

/**
* 
* @author joker 
* @date 创建时间：2018年8月18日 下午9:32:30
*/
@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
public class TmallUserException extends TmallBussinessException
{
	
	public TmallUserException(ErrorCodeEnum enum1)
	{
		super(enum1);
	}
	public TmallUserException(ErrorCodeEnum enum1,String msg)
	{
		super(enum1,msg);
	}
	public TmallUserException(ErrorCodeEnum enum1,Throwable cause)
	{
		super(enum1,cause);
	}
	


	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月18日 下午9:33:50
	*/
	private static final long serialVersionUID = -5744886035159116167L;

}
