/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月28日 下午7:38:13
* 
*/
package com.tmall.system.admin.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tmall.common.constants.AuthConstant;
import com.tmall.common.dto.ActionDTO;
import com.tmall.common.dto.MenuDTO;
import com.tmall.common.dto.PermissionDTO;
import com.tmall.common.dto.ResourceDTO;
import com.tmall.common.dto.RoleDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.system.admin.model.TmallUsernamePasswordToken;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月28日 下午7:38:13
 */
public class TmallAuthRealm extends AuthorizingRealm
{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// SimpleAuthenticationInfo authenticationInfo=(SimpleAuthenticationInfo)
		// principals.getPrimaryPrincipal();
		UserDTO userDTO = (UserDTO) principals.getPrimaryPrincipal();
		List<RoleDTO> roles = userDTO.getRoles();
		if (null == roles)
		{
			roles = new ArrayList<>();
		}
		roles.add(userDTO.getSelfRole());
		for (RoleDTO roleDTO : roles)
		{
			authorizationInfo.addRole(roleDTO.getRoleName());
			List<MenuDTO> menuDTOs = roleDTO.getMenus();
			menuDTOs.forEach(m->{
				authorizationInfo.addStringPermission(m.getMenuValue());
			});
			List<ActionDTO> actionDTOs = roleDTO.getPermisions();
			actionDTOs.forEach(a->{
				authorizationInfo.addStringPermission(a.getActionValue());
			});
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{

		TmallUsernamePasswordToken usernamePasswordToken = (TmallUsernamePasswordToken) token;
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		UserDTO userDTO = (UserDTO) usernamePasswordToken.getPrincipal();
		String credential = (String) token.getCredentials();
		request.getSession().setAttribute(AuthConstant.AUTH_IN_SESSION, credential);

		return new SimpleAuthenticationInfo(userDTO, credential, this.getName());
	}

	@Override
	public boolean supports(AuthenticationToken token)
	{
		return token instanceof TmallUsernamePasswordToken;
	}

}
