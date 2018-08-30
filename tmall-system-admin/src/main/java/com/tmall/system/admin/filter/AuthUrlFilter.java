/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月28日 下午9:03:30
* 
*/
package com.tmall.system.admin.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joker.library.utils.CommonUtils;
import com.tmall.common.constants.AuthConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.dto.AuthTokenDTO;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.utils.JWTUtils;
import com.tmall.common.utils.JsonUtils;
import com.tmall.system.admin.config.TmallAdminConfigProperty;
import com.tmall.system.admin.model.TmallUsernamePasswordToken;
import com.tmall.system.admin.util.ApplicationContextUtil;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月28日 下午9:03:30
 */
public class AuthUrlFilter implements Filter
{

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private JWTUtils jwtUtils;

	private String getServerUrl(String server)
	{
		ServiceInstance instance = loadBalancerClient.choose(server);
		String host = instance.getHost();
		int port = instance.getPort();
		// 这个http不能这样写,感觉
		return "http://" + host + ":" + port;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		// this.loadBalancerClient =
		// ApplicationContextUtil.getApplicationContext().getBean(LoadBalancerClient.class);
		//
		// this.tmallAdminConfigProperty =
		// ApplicationContextUtil.getApplicationContext()
		// .getBean(TmallAdminConfigProperty.class);
		// this.tmallAdminConfigProperty = WebApplicationContextUtils
		// .getWebApplicationContext(filterConfig.getServletContext()).getBean(TmallAdminConfigProperty.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{

		Subject subject = SecurityUtils.getSubject();
		if (null != subject && null != subject.getPrincipal())
		{
			chain.doFilter(request, response);
			return;
		}
		// if(null==loadBalancerClient)
		// {
		// this.loadBalancerClient =
		// ApplicationContextUtil.getApplicationContext().getBean(LoadBalancerClient.class);
		// }
		// if(null==tmallAdminConfigProperty)
		// {
		// this.tmallAdminConfigProperty =
		// ApplicationContextUtil.getApplicationContext()
		// .getBean(TmallAdminConfigProperty.class);
		// }
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String token = null;
		token = (String) req.getSession().getAttribute(AuthConstant.AUTH_IN_SESSION);
		if (StringUtils.isEmpty(token))
		{
			token = req.getParameter("token");
			if (StringUtils.isEmpty(token))
			{
				sendRedirect(req, resp);
				return;
			}
		}
		AuthTokenDTO authTokenDTO = jwtUtils.parseByAuthPublicKey(token);
		if (null == authTokenDTO || (System.currentTimeMillis()>authTokenDTO.getInvalidTime()))
		{
			//token过期
			sendRedirect(req, resp);
			return;
		} else
		{
			// 存在token,对token有效性校验
			UserDTO adminUserDTO = authTokenDTO.getData();
			subject.login(new TmallUsernamePasswordToken(adminUserDTO, token));
//			HttpSession session = req.getSession(true);
//			session.setAttribute(AuthConstant.AUTH_IN_SESSION, token);
			System.setProperty("auth.token", token);
			chain.doFilter(request, response);
			return;
		}
	}

	private void sendRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		// 这个storeAbbName是需要配置的,先写死,这个是需要写在全局的配置bootstrap中的
		String redirectUrl = CommonUtils.getRedirectUrlString(req);
		// 服务的名字从配置中取,或者定义一个统一的服务常量Constant
		String serverUrl = getServerUrl("SSO-SYSTEM");
		resp.sendRedirect(serverUrl + "/login?redirectUrl=" + URLEncoder.encode(redirectUrl, "utf-8")
				+ "&storeAbbName=tmall_admin");
	}

	@Override
	public void destroy()
	{

	}

}
