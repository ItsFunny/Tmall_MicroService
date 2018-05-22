/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午4:59:02
* 
*/
package com.micro.portal.filter;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.RequestGroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.micro.portal.config.KeyProperties;
import com.micro.portal.utils.PortalUtils;
import com.tmall.common.constants.RedisConstant;
import com.tmall.common.constants.SessionConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.model.User;
import com.tmall.common.service.INosqlService;
import com.tmall.common.utils.JsonUtils;
import com.tmall.common.utils.RSAUtils;
import com.tmall.login.client.service.FeginLoginServerService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午4:59:02
 */
public class CheckUserLoginFilter implements Filter
{
	@Autowired
	private KeyProperties keyProperties;
	@Autowired
	private INosqlService redisService;
	@Autowired
	private FeginLoginServerService feginLoginServerService;
	public CheckUserLoginFilter(KeyProperties keyProperties, INosqlService redisService,FeginLoginServerService serverService)
	{
		this.keyProperties = keyProperties;
		this.redisService = redisService;
		this.feginLoginServerService=serverService;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
	}


	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		Object isAuth = session.getAttribute(SessionConstant.markIsAuth);
//		if (null!=isAuth)
//		{
//			chain.doFilter(req, resp);
//			return;
//		}
		String token = request.getParameter("token");
		if (!StringUtils.isEmpty(token))
		{
			// 校验token的有效性
//			RestTemplate restTemplate = new RestTemplate();
//			String json = restTemplate.getForObj1ect(TmallURLConstant.TMALL_LOGIN_SSO_AUTH_TOKEN_URL + "?token=" + URLEncoder.encode(token, "utf-8"),
//					String.class);
			//既然用redis了,为啥不将redis作用共享的呢,子系统只有读的权限,就可以省略一个http调用了啊,如果是基于其他方式的话,就需要http调用了
			String primitiveToken = RSAUtils.decryptByPublic(token, keyProperties.getLoginPublicKey());
			if(StringUtils.isEmpty(primitiveToken))
			{
				response.sendRedirect(TmallURLConstant.TMALL_LOGIN_URL+"?redirectUrl="+PortalUtils.getRedirectUrl(request));
			}
			String json=redisService.get(String.format(RedisConstant.USER_INFO, primitiveToken));
			if (!StringUtils.isEmpty(json))
			{
				User user = JsonUtils.json2Object(json, User.class);
				session.setAttribute(SessionConstant.markIsAuth, true);
				session.setAttribute(SessionConstant.USER_IN_SESSION, user);
				chain.doFilter(req, resp);
				return;
			}
		}
		//传入的token无效的时候也会跳转到查询用户是否登录
		// 查询用户是否登录
//		response.sendRedirect(TmallURLConstant.TMALL_LOGIN_SSO_CHECKLOGIN_URL + "?redirectUrl="
//				+ PortalUtils.getRedirectUrl(request));
		response.sendRedirect(TmallURLConstant.TMALL_LOGIN_URL+"?redirectUrl="+PortalUtils.getRedirectUrl(request)+"&server="+PortalUtils.getServerBaseUrl(request));
	}
}
