/**
*
* @author joker 
* @date 创建时间：2018年6月4日 上午8:38:32
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.lang.Collections;
import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月4日 上午8:38:32
 */
@Data
public class UserDTO implements Serializable
{
	/**
	 * @author joker
	 * @date 创建时间：2018年6月4日 上午8:39:10
	 */
	private static final long serialVersionUID = -8708835819083673732L;
	private Long userId;
	private String username;
	private String realname;
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
	// private Integer roleId=0; //默认的roleId,表示普通用户
	// 2018-08-02 add
	private List<RoleDTO> roles;
	// 2018-08-05 add
	// 在当前店铺下的角色
	private RoleDTO selfRole;
	// 2018-06-21 12:55 add
	private Integer tableNum;
	// 2018-07-30 20:55 add
	private String token;
	// 2018-09-02 11:42 add 当前店铺店铺
	private StoreDTO belongTo;
	// 所属的所有店铺 关于这2个,应该是可以合并的
	private List<StoreDTO> stores;

	@JsonIgnore
	public boolean isSuperAdmin()
	{
		if (selfRole == null)
		{
			throw new RuntimeException("用户角色信息为空");
		}
		return selfRole.isSuperAdmin();
	}

	public UserDTO()
	{
		super();
		roles = new ArrayList<RoleDTO>();
		stores = new ArrayList<>();
	}

}
