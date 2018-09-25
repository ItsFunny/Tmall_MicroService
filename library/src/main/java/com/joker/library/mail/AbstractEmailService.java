/**
 * 
 */
package com.joker.library.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.joker.library.mail.property.EmailProperty;

/**
 * @author Administrator
 *
 */
public abstract class AbstractEmailService implements IEmailService
{
	protected Session session;
	protected EmailProperty emailProperty;
	/**
	 * @param smtp 
	 * @param host
	 * @param auth
	 * @param sendMailAccount 发送方的账户(由哪个账户发送)
	 * @param sendMailAccountPWD	发送方的账户密码
	 * @author joker 
	 * @date 创建时间：2018年3月4日 下午6:58:52
	 */
	public abstract void config(String smtp,String host,boolean auth,String sendMailAccount,String sendMailAccountPWD);
	public abstract void config(EmailProperty emailProperty);
	public  void config( )
	{
		if(emailProperty==null)
		{
			throw new RuntimeException("参数不可为空");
		}else if(emailProperty.isNull())
		{
			throw new RuntimeException("参数不可为空");
		}else {
			Properties properties=new Properties();
			properties.setProperty("mail.transport.protocol", emailProperty.getProtocol());
			properties.setProperty("mail.smtp.host", emailProperty.getHost());
			String smtpAuth="";
			if(emailProperty.isAuth())
			{
				smtpAuth="true";
			}else
			{
				smtpAuth="false";
			}
			properties.setProperty("mail.smtp.auth",smtpAuth);
			session = Session.getInstance(properties);
			session.setDebug(true);
		}
	}
	@Override
	public void sendMessage(String to,String subject,String content) throws UnsupportedEncodingException, MessagingException
	{
		MimeMessage message = createMimeMessage(emailProperty.getSendEmailAccount(), to, subject, content);
		Transport transport = session.getTransport();
		transport.connect(emailProperty.getSendEmailAccount(),emailProperty.getSendEmailPwd());
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	private  MimeMessage createMimeMessage(String from, String to,String subject,String content)
			throws UnsupportedEncodingException, MessagingException
	{
		MimeMessage message=new MimeMessage(session);
		try
		{
			//这里需要更改
			message.setFrom(new InternetAddress(from, "FromJoker", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress(to, "iiii", "utf-8"));
			message.setSubject(subject, "UTF-8");
			message.setContent(content, "text/html;charset=utf-8");
			message.setSentDate(new Date());
			message.saveChanges();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}
		return message;
	}
	public Session getSession()
	{
		return session;
	}
	public void setSession(Session session)
	{
		this.session = session;
	}
	public EmailProperty getEmailProperty()
	{
		return emailProperty;
	}
	public void setEmailProperty(EmailProperty emailProperty)
	{
		this.emailProperty = emailProperty;
	}
}
