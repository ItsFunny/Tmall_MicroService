/**
*
* @author joker 
* @date 创建时间：2018年5月21日 下午9:31:54
* 
*/
package org.login_server.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.login_server.config.KeyProperties;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.common.utils.RSAUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年5月21日 下午9:31:54
*/
public class TmallLoginRealm extends AuthorizingRealm
{
	@Autowired
	private KeyProperties keyProperties;
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection)
	{
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
	{
		String encryptedToken=(String) authenticationToken.getPrincipal();
		String publicKey=(String) authenticationToken.getCredentials();
		String primitiveKey = RSAUtils.decryptByPublic(encryptedToken, publicKey);
		
		return null;
	}

	@Override
	public boolean supports(AuthenticationToken token)
	{
		return super.supports(token);
	}

}
