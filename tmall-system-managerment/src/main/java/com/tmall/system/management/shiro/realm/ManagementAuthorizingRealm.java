/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午7:09:07
* 
*/
package com.tmall.system.management.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.common.dto.UserDTO;
import com.tmall.system.management.shiro.token.ManagementAuthenticationToken;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月21日 下午7:09:07
 */
public class ManagementAuthorizingRealm extends AuthorizingRealm
{
	//这里需要注入userService,查询数据库中用户所有的角色和权限及其资源信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		UserDTO userDTO=(UserDTO) principals.getPrimaryPrincipal();
		System.out.println(userDTO);
		info.addRole("admin");
		info.addStringPermission("add:product");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		ManagementAuthenticationToken managementAuthenticationToken = (ManagementAuthenticationToken) token;
		AuthenticationInfo info = new SimpleAuthenticationInfo(managementAuthenticationToken.getPrincipal(),
				managementAuthenticationToken.getCredentials(),super.getName());
		return info;
	}
	
	@Override
	public boolean supports(AuthenticationToken token)
	{
		return token instanceof ManagementAuthenticationToken;
	}
}
