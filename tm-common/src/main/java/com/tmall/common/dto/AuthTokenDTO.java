/**
*
* @author joker 
* @date 创建时间：2018年8月5日 下午10:07:23
* 
*/
package com.tmall.common.dto;

/**
* 
* @author joker 
* @date 创建时间：2018年8月5日 下午10:07:23
*/
public class AuthTokenDTO
{
	private UserDTO data;
	
	private String storeAbbName;
	
	private Long invalidTime;
	
	public UserDTO getData()
	{
		return data;
	}
	public void setData(UserDTO data)
	{
		this.data = data;
	}
	public Long getInvalidTime()
	{
		return invalidTime;
	}
	public void setInvalidTime(Long invalidTime)
	{
		this.invalidTime = invalidTime;
	}
	public String getStoreAbbName()
	{
		return storeAbbName;
	}
	public void setStoreAbbName(String storeAbbName)
	{
		this.storeAbbName = storeAbbName;
	}
	
}
