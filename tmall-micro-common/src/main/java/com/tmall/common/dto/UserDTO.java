/**
*
* @author joker 
* @date 创建时间：2018年6月4日 上午8:38:32
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月4日 上午8:38:32
 */
public class UserDTO implements Serializable
{
	/**
	 * @author joker
	 * @date 创建时间：2018年6月4日 上午8:39:10
	 */
	private static final long serialVersionUID = -8708835819083673732L;
	private Long userId;
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private String openid;
	private String IDCard;
	private String mobile;
	private Integer status;
	private Date createDate;
	private Date updateDate;
	private Date lastLoginDate;
	private String lastLoginIp;

	// 2018-06-21 12:40 add
//	private Integer roleId=0; //默认的roleId,表示普通用户
	// 2018-08-02 add
	private List<RoleDTO> roles;
	//2018-08-05 add
	//在当前店铺下的角色
	private RoleDTO selfRole;
	// 2018-06-21 12:55 add
	private Integer tableNum;
	// 2018-07-30 20:55 add
	private String token;
	
	
	@JsonIgnore
	public boolean isSuperAdmin()
	{
		if(selfRole==null)
		{
			throw new RuntimeException("用户角色信息为空");
		}
		return selfRole.isSuperAdmin();
	}

	public UserDTO()
	{
		super();
		roles=new ArrayList<RoleDTO>();
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getOpenid()
	{
		return openid;
	}

	public void setOpenid(String openid)
	{
		this.openid = openid;
	}

	public String getIDCard()
	{
		return IDCard;
	}

	public void setIDCard(String iDCard)
	{
		IDCard = iDCard;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
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

	public Date getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp()
	{
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp)
	{
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getTableNum()
	{
		return tableNum;
	}

	public void setTableNum(Integer tableNum)
	{
		this.tableNum = tableNum;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}


	public List<RoleDTO> getRoles()
	{
		return roles;
	}

	public void setRoles(List<RoleDTO> roles)
	{
		this.roles = roles;
	}

	public RoleDTO getSelfRole()
	{
		return selfRole;
	}

	public void setSelfRole(RoleDTO selfRole)
	{
		this.selfRole = selfRole;
	}
}
