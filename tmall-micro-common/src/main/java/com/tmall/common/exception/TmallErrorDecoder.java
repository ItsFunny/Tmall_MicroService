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
			Object detailObj = map.get("msg");
			if (null != detailObj)
			{
				String detail = detailObj.toString();
				String[] strings = detail.split(":");
				Integer code = Integer.parseInt(strings[0]);
				exception=new TmallBussinessException(ErrorCodeEnum.getEnum(code));
			}else {
				exception=new TmallBussinessException(ErrorCodeEnum.UNKNOWN_EXCEPTION);
			}
		} catch (IOException e)
		{
			logger.error("parse rmi exception occur error");
		}
		return exception != null ? exception
				: new TmallBussinessException(ErrorCodeEnum.UNKNOWN_EXCEPTION);
	}
}
