/**
*
* @author joker 
* @date 创建时间：2018年5月20日 上午9:01:43
* 
*/
package org.login_server.filter;

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

import com.tmall.common.constants.CookieConstant;
import com.tmall.common.constants.SessionConstant;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.enums.TmallUrlEnum;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.util.WebUtils;
import org.login_server.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月20日 上午9:01:43
 */
public class LoginFilter implements Filter
{

	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		Object isAuth = session.getAttribute(SessionConstant.markIsAuth);
		System.out.println(session.getAttribute(SessionConstant.USER_LOGIN_TOKEN_IN_SESSION));
		// String encryptedToken = CookieUtils.getUserToken(request,
		// CookieConstant.USER_TOKEN);
		if (null != isAuth)
		{
			String encryptedToken = (String) session.getAttribute(SessionConstant.USER_LOGIN_TOKEN_IN_SESSION);
			String redirectUrl = StringUtils.isEmpty(request.getParameter("redirectUrl"))
					? TmallUrlEnum.TMALL_PORTAL.getLoginNotifyUrl() + "?"
					: request.getParameter("redirectUrl");
			HttpServletResponse response = (HttpServletResponse) arg1;
			response.sendRedirect(redirectUrl + "token=" + URLEncoder.encode(encryptedToken, "utf-8"));
		} else
		{
			arg2.doFilter(arg0, arg1);
		}
		// if (null == isAuth || !(boolean) isAuth)
		// {
		// // 没有登录,则继续传递下去,让其跳转到登录页面
		// arg2.doFilter(arg0, arg1);
		// } else
		// {
		// // 如果已经登录了,则返回全局的token
		// String redirectUrl = request.getParameter("redirectUrl");
		//// String encryptedToken = CookieUtils.getUserToken(request,
		// CookieConstant.USER_TOKEN);
		// String encryptedToken=(String)
		// session.getAttribute(SessionConstant.USER_LOGIN_TOKEN_IN_SESSION);
		// HttpServletResponse response = (HttpServletResponse) arg1;
		// response.sendRedirect(redirectUrl + "token=" + encryptedToken);
		// }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
