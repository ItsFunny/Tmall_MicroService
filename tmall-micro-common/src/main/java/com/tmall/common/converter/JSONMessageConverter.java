package com.tmall.common.converter;
///**
//*
//* @author joker 
//* @date 创建时间：2018年5月16日 上午10:37:03
//* 
//*/
//package com.rebuildtmall.tmall_micro_common.converter;
//
//import java.io.Serializable;
//import java.io.UnsupportedEncodingException;
//
//import org.apache.commons.collections.functors.IfClosure;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.support.converter.MessageConversionException;
//import org.springframework.amqp.support.converter.MessageConverter;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonSyntaxException;
//import com.google.gson.reflect.TypeToken;
//import com.rebuildtmall.tmall_micro_common.event.AppEvent;
//import com.rebuildtmall.tmall_micro_common.event.GsonInterfaceAdapter;
//import com.rebuildtmall.tmall_micro_common.utils.JsonUtils;
//
//import net.sf.json.JSONObject;
//
///**
// * 
// * @author joker
// * @date 创建时间：2018年5月16日 上午10:37:03
// */
//public class JSONMessageConverter implements MessageConverter
//{
//
//	@Override
//	public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException
//	{
//
//		// String json = JSONObject.fromObject(object).toString();
//		// // JSONObject fromObject = JSONObject.fromObject(object);
//		// // String json = fromObject.toString();
//		// // String json = JsonUtils.obj2Json(object);
//		// Message message;
//		// try
//		// {
//		// message = new Message(json.getBytes("utf-8"), messageProperties);
//		// return message;
//		// } catch (UnsupportedEncodingException e)
//		// {
//		// e.printStackTrace();
//		// }
//		// return null;
//
//		String json = JSON.toJSONString(object);
//		try
//		{
//			return new Message(json.getBytes("utf-8"), messageProperties);
//		} catch (UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public Object fromMessage(Message message) throws MessageConversionException
//	{
//		byte[] body = message.getBody();
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.registerTypeAdapter(Serializable.class, new GsonInterfaceAdapter<Serializable>());
//		Gson gson = gsonBuilder.create();
//		try
//		{
//			AppEvent appEvent = gson.fromJson(new String(body, "utf-8"), AppEvent.class);
//			return appEvent;
//		} catch (JsonSyntaxException | UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
