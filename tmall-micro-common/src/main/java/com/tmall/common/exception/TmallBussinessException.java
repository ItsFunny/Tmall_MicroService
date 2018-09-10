/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午4:46:41
* 
*/
package com.tmall.common.exception;


import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.tmall.common.enums.ErrorCodeEnum;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月18日 下午4:46:41
 */
@Data
public class TmallBussinessException extends HystrixBadRequestException
{
	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年8月18日 下午4:46:54
	 */
	private static final long serialVersionUID = -5076254306303975358L;
	// 异常码
	private int code;

	// 异常信息
	private String message;
	
	//状态码
	private Integer status;


	public TmallBussinessException(String message, Throwable cause)
	{
		super(message, cause);
		this.message = message;
	}

	
	public TmallBussinessException(ErrorCodeEnum enum1)
	{
		super(enum1.getMsg());
		this.code=enum1.getCode();
		
		this.message=enum1.getMsg();
	}
	public TmallBussinessException(ErrorCodeEnum enum1,String msg)
	{
		super(msg);
		this.code=enum1.getCode();
		this.message=enum1.getMsg()+msg;
	}
	public TmallBussinessException(ErrorCodeEnum enum1,Throwable cause)
	{
		super(enum1.getMsg(),cause);
		this.code=enum1.getCode();
		this.message=enum1.getMsg()+","+cause.getMessage();
	}


}
