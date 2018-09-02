/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午10:55:41
* 
*/
package com.tmall.common.enums;

/**
* server-auth服务1开头
* server-user服务2开头
* server-store服务3开头
* server-user服务4开头
* system-admin系统 SYS开头 1开头
* @author joker 
* @date 创建时间：2018年8月21日 下午10:55:41
*/
public enum ErrorCodeEnum
{
	UNKNOWN_EXCEPTION(0,"服务故障,未知原因"),
	
	/*
	 * auth-server
	 */
	AUTH_MULTIROLES_1001(1001,"同个店铺匹配到多个角色信息"),
	
	/*
	 * user-server
	 */
	USER_NOT_EXIST_2001(2001,"用户不存在"),
	USER_TOKEN_INVALID(2002,"无效的用户token信息"),
	USER_TOKEN_EXPIRED(2003,"用户token信息过期,重新登录"),
	USER_NOT_LOGIN(2004,"用户尚未登录"),
	/*
	 * store-server
	 */
	STORE_INTERNAL_SERVER_3001(3001,"服务调用,内服服务出错"),
	STORE_NOT_EXIST_3002(3002,"店铺信息不存在"),
	

	
	
	SYS_ADMIN_SERVER_10001(10001,"服务内部错误"),
	;
	
	
	public static ErrorCodeEnum getEnum(int code)
	{
		ErrorCodeEnum[] enums = ErrorCodeEnum.values();
		for (ErrorCodeEnum errorCodeEnum : enums)
		{
			if(errorCodeEnum.getCode().equals(Integer.valueOf(code)))
			{
				return errorCodeEnum;
			}
		}
		return ErrorCodeEnum.UNKNOWN_EXCEPTION;
	}
	
	
	private Integer code;
	
	private String msg;

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	private ErrorCodeEnum(Integer code, String msg)
	{
		this.code = code;
		this.msg=code+":"+msg;
	}
	
	
}
