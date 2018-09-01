/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午12:34:52
* 
*/
package com.tmall.common.interceptor;





import java.util.Collection;
import java.util.Map;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.jsonwebtoken.lang.Collections;

/**
* 在feign请求时做一些额外的配置
* @author joker 
* @date 创建时间：2018年8月18日 下午12:34:52
*/

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor
{
	//添加自定义的请求头,这里添加的是token
	@Override
	public void apply(RequestTemplate template)
	{
		Map<String, Collection<String>> headers = template.headers();
		if(!headers.containsKey("Authorization")||Collections.isEmpty(headers.get("Authorization")))
		{
			template.header("Authorization", System.getProperty("auth.token"));
		}
	}

}
