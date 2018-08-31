/**
*
* @author joker 
* @date 创建时间：2018年5月17日 下午5:46:10
* 
*/
package com.tmall.common.enums;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月17日 下午5:46:10
 */
public enum TmallUrlEnum
{
	TMALL_PORTAL("http://localhost:8001/index", "http:localhost:8001/login-notify",
			"http:localhost:8001/logout-notify","http:localhost:8001"),;
	private String indexUrl;
	private String loginNotifyUrl;
	private String logoutNotifyUrl;
	private String serverUrl;

	public String getIndexUrl()
	{
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl)
	{
		this.indexUrl = indexUrl;
	}

	public String getLoginNotifyUrl()
	{
		return loginNotifyUrl;
	}

	public void setLoginNotifyUrl(String loginNotifyUrl)
	{
		this.loginNotifyUrl = loginNotifyUrl;
	}

	public String getLogoutNotifyUrl()
	{
		return logoutNotifyUrl;
	}

	public void setLogoutNotifyUrl(String logoutNotifyUrl)
	{
		this.logoutNotifyUrl = logoutNotifyUrl;
	}

	private TmallUrlEnum(String indexUrl, String loginNotifyUrl, String logoutNotifyUrl, String serverUrl)
	{
		this.indexUrl = indexUrl;
		this.loginNotifyUrl = loginNotifyUrl;
		this.logoutNotifyUrl = logoutNotifyUrl;
		this.serverUrl = serverUrl;
	}

	public String getServerUrl()
	{
		return serverUrl;
	}

	public void setServerUrl(String serverUrl)
	{
		this.serverUrl = serverUrl;
	}

}
