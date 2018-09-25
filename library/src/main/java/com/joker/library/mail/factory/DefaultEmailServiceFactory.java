/**
*
* @author joker 
* @date 创建时间：2018年6月10日 下午6:08:14
* 
*/
package com.joker.library.mail.factory;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.joker.library.mail.DefalutEmailServiceImpl;
import com.joker.library.mail.IEmailService;
import com.joker.library.mail.property.EmailProperty;

/**
* 
* @author joker 
* @date 创建时间：2018年6月10日 下午6:08:14
*/
public class DefaultEmailServiceFactory extends AbstractEmailFactory
{

	@Override
	public IEmailService genereateEmailService(EmailProperty emailProperty)
	{
		DefalutEmailServiceImpl defalutEmailServiceImpl=new DefalutEmailServiceImpl();
		defalutEmailServiceImpl.config(emailProperty);
		return defalutEmailServiceImpl;
	}
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException
	{
		DefaultEmailServiceFactory defaultEmailServiceFactory=new DefaultEmailServiceFactory();
		EmailProperty emailProperty=new EmailProperty();
		emailProperty.setProtocol("smtp");
		emailProperty.setHost("smtp.163.com");
		emailProperty.setAuth(true);
		emailProperty.setSendEmailAccount("18757883747@163.com");
		emailProperty.setSendEmailPwd("lvcong1215");
		IEmailService emailService = defaultEmailServiceFactory.genereateEmailService(emailProperty);
		emailService.sendMessage("623691249@qq.com", "淘宝网", "ok 啦");
	}

}
