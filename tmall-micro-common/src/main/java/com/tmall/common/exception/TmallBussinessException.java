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
//	// 未认证
//	public static final int UNAUTHENTICATED_EXCEPTION = 401;
//	// 未授权
//	public static final int FORBIDDEN_EXCEPTION = 403;
//	// 超时
//	public static final int TIMEOUT_EXCEPTION = 2;
//	// 业务逻辑异常
//	public static final int BIZ_EXCEPTION = 3;
//	// 未知异常->系统异常
//	public static final int UNKNOWN_EXCEPTION = 4;
//	//缺少必要参数
//	public static final int MISSING_ARGUMENT_EXCEPTION=5;
//	
//	public static final int ILLEGAL_ARGUMETN_EXCEPTION=6;
//	//bean转换时候出错
//	public static final int PARSE_BEAN_EXCEPTION=7;
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
