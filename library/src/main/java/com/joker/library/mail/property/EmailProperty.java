/**
*
* @author joker 
* @date 创建时间：2018年3月19日 下午4:24:22
* 
*/
package com.joker.library.mail.property;

/**
 * 
 * @author joker
 * @date 创建时间：2018年3月19日 下午4:24:22
 */
public class EmailProperty
{
	private String subject;
	private String content;
	private boolean auth;
	private String sendEmailAccount;
	private String sendEmailPwd;
	private String host;
	private String protocol;

	public EmailProperty(boolean auth, String sendEmailAccount, String sendEmailPWD, String host)
	{
		super();
		this.auth = auth;
		this.sendEmailAccount = sendEmailAccount;
		this.sendEmailPwd = sendEmailPWD;
		this.host = host;
	}

	public EmailProperty()
	{
		super();
	}


	public boolean isNull()
	{
		if ("".equals(sendEmailAccount) || sendEmailAccount == null || "".equals(sendEmailPwd) || sendEmailPwd == null
				|| "".equals(host) || host == null)
		{
			return true;
		}
		return false;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public boolean isAuth()
	{
		return auth;
	}

	public void setAuth(boolean auth)
	{
		this.auth = auth;
	}

	public String getSendEmailAccount()
	{
		return sendEmailAccount;
	}

	public void setSendEmailAccount(String sendEmailAccount)
	{
		this.sendEmailAccount = sendEmailAccount;
	}

	public String getSendEmailPwd()
	{
		return sendEmailPwd;
	}

	public void setSendEmailPwd(String sendEmailPWD)
	{
		this.sendEmailPwd = sendEmailPWD;
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

}
