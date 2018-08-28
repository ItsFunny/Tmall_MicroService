/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午4:53:24
* 
*/
package com.tmall.common.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.tmall.common.utils.JsonUtils;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

/**
* 
* @author joker 
* @date 创建时间：2018年8月18日 下午4:53:24
*/
public class TmallErrorDecoder implements ErrorDecoder 
{
	private Logger logger=LoggerFactory.getLogger(TmallErrorDecoder.class);
	@Override
	public Exception decode(String methodKey, Response response)
	{
		Exception exception=null;
		try
		{
			String json = Util.toString(response.body().asReader());
			
			if(response.status()==HttpStatus.UNAUTHORIZED.value())
			{
				exception=JsonUtils.json2Object(json, TmallUserException.class);
			}else {
				exception=JsonUtils.json2Object(json, TmallBussinessException.class);
			}
		} catch (IOException e)
		{
			logger.error("parse rmi exception occur error");
		}
		return exception!=null?exception:new TmallBussinessException(TmallBussinessException.UNKNOWN_EXCEPTION,"系统运行异常");
	}
}
