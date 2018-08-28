/**
*
* @author joker 
* @date 创建时间：2018年5月16日 下午4:56:20
* 
*/
package com.rebuildtmall.tmall_batch.model;

import java.util.Date;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月16日 下午4:56:20
 */
public class UserOffsiteRecord
{
	private String id;
	private Long userId;
	private String ip;
	private Date createDate;

	public UserOffsiteRecord()
	{
		this.createDate=new Date();
	}
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

}
