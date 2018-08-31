/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午9:21:32
* 
*/
package com.tmall.server.user.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.common.dto.UserDTO;
import com.tmall.server.user.common.model.TmallUser;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年7月29日 下午9:21:32
 */
public abstract class AbstractLoginHandler implements ILoginHandler
{
	public static final String EMAIL_TYPE = "email";

	public static final String MOBILE_TYPE = "mobile";

	@Autowired
	protected IUserService userService;

	@Autowired
	protected ILoginService loginService;

	public static class FormUser
	{
		private String loginKey;
		private String password;

		private String storeAbbName;
		// 是邮箱登录呢还是手机登录呢
		private String type;

		public void parse()
		{
			if (loginKey.contains("@"))
			{
				this.type = AbstractLoginHandler.EMAIL_TYPE;
			} else
			{
				this.type = AbstractLoginHandler.MOBILE_TYPE;
			}
		}

		public String getLoginKey()
		{
			return loginKey;
		}

		public void setLoginKey(String loginKey)
		{
			this.loginKey = loginKey;
		}

		public String getPassword()
		{
			return password;
		}

		public void setPassword(String password)
		{
			this.password = password;
		}

		public String getType()
		{
			return type;
		}

		public void setType(String type)
		{
			this.type = type;
		}

		public String getStoreAbbName()
		{
			return storeAbbName;
		}

		public void setStoreAbbName(String storeAbbName)
		{
			this.storeAbbName = storeAbbName;
		}
	}

	public AbstractLoginHandler(String type)
	{
		this.type = type;
	}

	public void init(IUserService userService, ILoginService loginService)
	{
		this.userService = userService;
		this.loginService = loginService;
	}

	protected String type;

	protected AbstractLoginHandler nextHandler;

	@Override
	public UserDTO findUser(FormUser formUser)
	{

		if (formUser.getType().equals(this.type))
		{
			TmallUser handler = this.handler(formUser);
			return parse(handler);
		} else if (this.nextHandler != null)
		{
			return this.nextHandler.findUser(formUser);
		} else
		{
			throw new RuntimeException("没有多找对应的登录负责人");
		}
	}

	private UserDTO parse(TmallUser user)
	{
		if (null == user)
		{
			return null;
		}
		UserDTO userDTO = new UserDTO();
		user.to(userDTO);
		return userDTO;
	}

	protected abstract TmallUser handler(FormUser formUser);

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public AbstractLoginHandler getNextHandler()
	{
		return nextHandler;
	}

	public void setNextHandler(AbstractLoginHandler nextHandler)
	{
		this.nextHandler = nextHandler;
	}

	public IUserService getUserService()
	{
		return userService;
	}

	@Autowired
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}

	public ILoginService getLoginService()
	{
		return loginService;
	}

	@Autowired
	public void setLoginService(ILoginService loginService)
	{
		this.loginService = loginService;
	}

}
