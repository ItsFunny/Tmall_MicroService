/**
*
* @author joker 
* @date 创建时间：2018年5月18日 下午7:40:07
* 
*/
package com.tmall.common.constants;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月18日 下午7:40:07
 */
public interface SecurityConstant
{
	String HEADER_SIGN = "SIGN";
	/*
	 * 用于在响应头上保存签名的原始数据
	 */
	String HEADER_PRIMITIVE="PRIMITIVE";
	/*
	 * 用于在响应头上添加签名,value是签名信息
	 */
	String LOGIN_LOGO=ServerEnum.LOGIN.getLogo();
	enum ServerEnum
	{
		LOGIN("login_logo")
		;
		private String logo;
		
		private ServerEnum(String logo)
		{
			this.logo = logo;
		}

		public String getLogo()
		{
			return logo;
		}
		public void setLogo(String logo)
		{
			this.logo = logo;
		}


	}
}
