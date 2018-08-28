/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午11:54:31
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限DTO
 * 
 * @author joker
 * @date 创建时间：2018年8月21日 下午11:54:31
 */
public class ActionDTO implements Serializable
{
	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年8月21日 下午11:55:35
	 */
	private static final long serialVersionUID = 1796414827740543008L;

	private Long actionId;

	private Integer actionVersion;

	private String actionUrl;

	private String actionName;

	private String actionValue;

	private Integer actionStatus;

	private String actionDescription;

	private Long menuId;

	private String createor;

	private Long createorId;

	private String lastOperator;

	private Long lastOperatorId;

	private Date createDate;

	private Date updateDate;

	public Long getActionId()
	{
		return actionId;
	}

	public void setActionId(Long actionId)
	{
		this.actionId = actionId;
	}

	public Integer getActionVersion()
	{
		return actionVersion;
	}

	public void setActionVersion(Integer actionVersion)
	{
		this.actionVersion = actionVersion;
	}

	public String getActionUrl()
	{
		return actionUrl;
	}

	public void setActionUrl(String actionUrl)
	{
		this.actionUrl = actionUrl;
	}

	public String getActionName()
	{
		return actionName;
	}

	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	public String getActionValue()
	{
		return actionValue;
	}

	public void setActionValue(String actionValue)
	{
		this.actionValue = actionValue;
	}

	public Integer getActionStatus()
	{
		return actionStatus;
	}

	public void setActionStatus(Integer actionStatus)
	{
		this.actionStatus = actionStatus;
	}

	public String getActionDescription()
	{
		return actionDescription;
	}

	public void setActionDescription(String actionDescription)
	{
		this.actionDescription = actionDescription;
	}

	public Long getMenuId()
	{
		return menuId;
	}

	public void setMenuId(Long menuId)
	{
		this.menuId = menuId;
	}

	public String getCreateor()
	{
		return createor;
	}

	public void setCreateor(String createor)
	{
		this.createor = createor;
	}

	public Long getCreateorId()
	{
		return createorId;
	}

	public void setCreateorId(Long createorId)
	{
		this.createorId = createorId;
	}

	public String getLastOperator()
	{
		return lastOperator;
	}

	public void setLastOperator(String lastOperator)
	{
		this.lastOperator = lastOperator;
	}

	public Long getLastOperatorId()
	{
		return lastOperatorId;
	}

	public void setLastOperatorId(Long lastOperatorId)
	{
		this.lastOperatorId = lastOperatorId;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
