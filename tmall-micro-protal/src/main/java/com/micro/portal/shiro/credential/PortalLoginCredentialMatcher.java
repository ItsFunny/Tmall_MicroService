/**
*
* @author joker 
* @date 创建时间：2018年5月24日 下午2:33:27
* 
*/
package com.micro.portal.shiro.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
* 
* @author joker 
* @date 创建时间：2018年5月24日 下午2:33:27
*/
public class PortalLoginCredentialMatcher implements CredentialsMatcher
{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
	{
		return true;
	}

}
