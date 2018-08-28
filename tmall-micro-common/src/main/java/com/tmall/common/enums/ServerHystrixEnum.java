/**
*
* @author joker 
* @date 创建时间：2018年5月19日 下午5:41:51
* 
*/
package com.tmall.common.enums;

/**
* 
* @author joker 
* @date 创建时间：2018年5月19日 下午5:41:51
*/
public enum ServerHystrixEnum
{
	LOGIN_DOWN("server is busy,try later"),
	;
	private String error;

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	private ServerHystrixEnum(String error)
	{
		this.error = error;
	}
	
}
