/**
*
* @author joker 
* @date 创建时间：2018年5月25日 下午1:32:56
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;

import com.tmall.common.enums.RestAPIStatus;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 下午1:32:56
 */
public class ResultDTO<T> implements Serializable
{
	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月15日 下午9:05:02
	*/
	private static final long serialVersionUID = -2218913589087908206L;
	private T data;
	private String msg;
	private Integer code;

	
	public boolean isSuccess()
	{
		return this.code==RestAPIStatus.SUCESS.ordinal();
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public ResultDTO<T> sucess(T data, String msg)
	{
		ResultDTO<T> ResultDTO = new ResultDTO<>();
		ResultDTO.setCode(RestAPIStatus.SUCESS.ordinal());
		ResultDTO.setData(data);
		ResultDTO.setMsg(msg);
		return ResultDTO;
	}

	public ResultDTO<T> sucess(T data)
	{
		return sucess(data, "sucess");
	}

}
