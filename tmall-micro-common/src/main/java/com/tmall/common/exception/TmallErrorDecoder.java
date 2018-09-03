/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午4:53:24
* 
*/
package com.tmall.common.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.library.utils.JsonUtil;
import com.tmall.common.enums.ErrorCodeEnum;

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
	private Logger logger = LoggerFactory.getLogger(TmallErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response)
	{
		Exception exception = null;
		try
		{
			// InputStream in = response.body().asInputStream();
			// int index=0;
			// StringBuilder sb=new StringBuilder();
			// while((index=in.read())!=-1)
			// {
			// sb.append((char)index);
			// }
			String json = Util.toString(response.body().asReader());
			Map<Object, Object> map = JsonUtil.json2Map(json, HashMap.class);
			Double codeObj = (Double) map.get("code");
			Object detailObj = map.get("msg");
			String errorMsg = detailObj == null ? "空指针错误" : detailObj.toString();
			if (Integer.valueOf(codeObj.intValue()).equals(0))
			{
				exception = new TmallBussinessException(ErrorCodeEnum.UNKNOWN_EXCEPTION, errorMsg);
				return exception;
			} else
			{
				Integer code = Integer.parseInt(codeObj.toString());
				if (code >= 2000 && code < 3000)
				{
					exception = new TmallUserException(ErrorCodeEnum.getEnum(code));
				} else
				{
					exception = new TmallBussinessException(ErrorCodeEnum.getEnum(code), errorMsg);
				}
			}
			// if (null != detailObj)
			// {
			// String detail = detailObj.toString();
			// String[] strings = detail.split(":");
			// if(strings.length<1)
			// {
			// exception=new RuntimeException("抛出格式错误,格式必须为 code:detail 格式");
			// return exception;
			// }
			// Integer code = Integer.parseInt(strings[0]);
			// if(isBeginWith(strings[0], '2'))
			// {
			// exception=new TmallUserException(ErrorCodeEnum.getEnum(code));
			// }else {
			// exception=new TmallBussinessException(ErrorCodeEnum.getEnum(code));
			// }
			//
			// }else {
			// Object msg=map.get("message");
			// exception=new
			// TmallBussinessException(ErrorCodeEnum.UNKNOWN_EXCEPTION,msg==null?"未知错误,系统异常":msg.toString());
			// }
		} catch (IOException e)
		{
			logger.error("parse rmi exception occur error");
		}
		return exception != null ? exception : new TmallBussinessException(ErrorCodeEnum.UNKNOWN_EXCEPTION);
	}

	public boolean isBeginWith(String string, char c)
	{
		char[] arr = string.toCharArray();
		return ((Character) arr[0]).equals(c);
	}

}
