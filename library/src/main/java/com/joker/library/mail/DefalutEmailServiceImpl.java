/**
 * 
 */
package com.joker.library.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.joker.library.mail.property.EmailProperty;

/**
 * @author Administrator
 *
 */
public class DefalutEmailServiceImpl extends AbstractEmailService 
{

	@Override
	public void config(String smtp, String host, boolean auth,String sendMailAccount,String sendMailAccountPWD)
	{
		EmailProperty emailProperty=new EmailProperty();
		emailProperty.setSendEmailAccount(sendMailAccount);
		emailProperty.setSendEmailPwd(sendMailAccountPWD);
		emailProperty.setProtocol(smtp);
		emailProperty.setHost(host);
		emailProperty.setAuth(auth);
		super.setEmailProperty(emailProperty);
		super.config();
	}
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException
	{
		DefalutEmailServiceImpl emailServiceImpl=new DefalutEmailServiceImpl();
		emailServiceImpl.config("smtp", "smtp.163.com", true, "18757883747@163.com", "lvcong1215");
		emailServiceImpl.sendMessage("623691249@qq.com", "淘宝网", "qqq");
	}
	@Override
	public void config(EmailProperty emailProperty)
	{
		super.setEmailProperty(emailProperty);
		super.config();
	}
}
