/**
*
* @author joker 
* @date 创建时间：2018年6月21日 下午6:45:07
* 
*/
package com.tmall.system.management.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.StringUtils;

import com.tmall.common.constants.SessionConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JsonUtils;
import com.tmall.common.utils.RSAUtils;
import com.tmall.system.management.config.KeyProperty;
import com.tmall.system.management.shiro.token.ManagementAuthenticationToken;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月21日 下午6:45:07
 */
public class TempFilter extends AuthenticationFilter implements BeanFactoryAware
{
	private BeanFactory beanFactory;
	private INosqlService redisService;
	private KeyProperty keyProperty;
	private String loginUrl;

	@Override
	protected boolean onAccessDenied(ServletRequest req, ServletResponse resp) throws Exception
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		HttpSession session = request.getSession(true);
		Object markIsAuth = session.getAttribute(SessionConstant.markIsAuth);
		if (null != markIsAuth)
		{
			return true;
		}
		String encryptToken = request.getParameter("token");
		if (StringUtils.isEmpty(encryptToken))
		{
			sendRedirect(response);
			return false;
		}
//		UserDTO user=new UserDTO();
//		user.setPassword("123");
		String previousToken = RSAUtils.decryptByPublic(encryptToken, keyProperty.getLoginPublicKey());
		String userJson = redisService.get(previousToken);
		if (!StringUtils.isEmpty(userJson))
		{
			UserDTO user = JsonUtils.json2Object(userJson, UserDTO.class);
			Subject subject = SecurityUtils.getSubject();
			ManagementAuthenticationToken token = new ManagementAuthenticationToken();
			token.setPrincipal(user);
			token.setCredentials(user.getPassword());
			subject.login(token);
			return true;
		}
		sendRedirect(response);
		return false;
	}
	private void sendRedirect(HttpServletResponse response) throws IOException
	{
		response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		response.sendRedirect(StringUtils.isEmpty(loginUrl) ? TmallURLConstant.TMALL_LOGIN_URL : loginUrl);
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException
	{
		this.beanFactory = beanFactory;
	}

	public INosqlService getRedisService()
	{
		return redisService;
	}

	public void setRedisService(INosqlService redisService)
	{
		this.redisService = redisService;
	}

	public KeyProperty getKeyProperty()
	{
		return keyProperty;
	}

	public void setKeyProperty(KeyProperty keyProperty)
	{
		this.keyProperty = keyProperty;
	}

	public String getLoginUrl()
	{
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl)
	{
		this.loginUrl = loginUrl;
	}

	public BeanFactory getBeanFactory()
	{
		return beanFactory;
	}

}
