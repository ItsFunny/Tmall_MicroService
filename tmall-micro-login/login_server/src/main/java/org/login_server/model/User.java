/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午9:21:27
* 
*/
package org.login_server.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 上午9:21:27
 */
public class User implements Serializable
{
	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年5月14日 上午9:23:28
	 */

	private static final long serialVersionUID = 2240562186622070329L;

	public User()
	{
	}

	private Long userId;
	private String username;
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

	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", openid=" + openid + ", IDCard=" + IDCard + ", mobile=" + mobile + ", status=" + status
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", lastLoginDate=" + lastLoginDate
				+ ", lastLoginIp=" + lastLoginIp + "]";
	}

}
