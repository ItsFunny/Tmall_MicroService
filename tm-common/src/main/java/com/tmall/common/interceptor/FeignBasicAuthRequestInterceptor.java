/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午12:34:52
* 
*/
package com.tmall.common.interceptor;





import feign.RequestInterceptor;
import feign.RequestTemplate;

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
		template.header("Authorization", System.getProperty("auth.token"));
	}

}
