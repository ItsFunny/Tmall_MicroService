/**
 * 
 */
package com.joker.library.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

/**
 * @author Administrator
 *
 */
public interface IEmailService
{
	void sendMessage(String to,String subject,String content) throws UnsupportedEncodingException, MessagingException;
	
}
