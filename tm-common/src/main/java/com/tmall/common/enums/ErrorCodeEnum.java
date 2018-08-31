/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午10:55:41
* 
*/
package com.tmall.common.enums;

/**
* 
* @author joker 
* @date 创建时间：2018年8月21日 下午10:55:41
*/
public enum ErrorCodeEnum
{
	
	AUTH1001(1001,"同个店铺匹配到多个角色信息"),
	
	;
	
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
		this.msg = msg;
	}
	
	
}
