/**
*
* @author joker 
* @date 创建时间：2018年6月10日 下午5:38:11
* 
*/
package com.joker.library.mail.factory;

import com.joker.library.mail.IEmailService;
import com.joker.library.mail.property.EmailProperty;

/**
* 
* @author joker 
* @date 创建时间：2018年6月10日 下午5:38:11
*/
public abstract class AbstractEmailFactory
{
	public abstract IEmailService genereateEmailService(EmailProperty emailProperty);
	
}
