/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午11:55:47
* 
*/
package com.joker.library.mq.converters;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import com.joker.library.mq.AppEvent;
import com.joker.library.utils.JsonUtil;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月8日 下午11:55:47
 */
public class JSONMessageConverter implements MessageConverter
{

	@Override
	public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException
	{
		String json = JsonUtil.obj2Json(object);
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		try
		{
			Message message=new Message(json.getBytes("utf-8"), messageProperties);
			return message;
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("转换出错", e);
		}
	}

	@Override
	public Object fromMessage(Message message) throws MessageConversionException
	{
		try
		{
			String json = new String(message.getBody(), "utf-8");
			Object event = JsonUtil.json2Object(json, Object.class);
			return event;
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("json转换出错", e);
		}
	}

}
