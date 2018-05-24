/**
*
* @author joker 
* @date 创建时间：2018年5月24日 下午2:06:00
* 
*/
package com.micro.portal.shiro.realm;

import java.io.Serializable;

import org.apache.commons.math.ode.nonstiff.AdamsBashforthIntegrator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.micro.portal.config.KeyProperties;
import com.micro.portal.shiro.authentication.EncryptAuthenticationToken;
import com.tmall.common.model.User;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月24日 下午2:06:00
 */
public class PortalLoginRealm extends AuthorizingRealm
{
	@Autowired
	private KeyProperties keyProperties;

	/*
	 * 采用缓存,缓存用户的权限和角色信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("admin");
		authorizationInfo.addRole("role1");
		authorizationInfo.addStringPermission("permission1");
		authorizationInfo.addStringPermission("permission2");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		EncryptAuthenticationToken token2 = (EncryptAuthenticationToken) token;
		User user = (User) token2.getData();
		return new SimpleAuthenticationInfo(user, token2.getEncryptToken(), this.getName());
	}

	@Override
	public boolean supports(AuthenticationToken token)
	{
		return token instanceof EncryptAuthenticationToken;
	}

}
