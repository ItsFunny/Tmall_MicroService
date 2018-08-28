/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午11:30:32
* 
*/
package com.tmall.gateway.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
* 
* @author joker 
* @date 创建时间：2018年8月28日 上午11:30:32
*/
public class AuthFilter extends ZuulFilter
{

	@Override
	public boolean shouldFilter()
	{
		return false;
	}

	@Override
	public Object run() throws ZuulException
	{
		return null;
	}

	@Override
	public String filterType()
	{
		return null;
	}

	@Override
	public int filterOrder()
	{
		return 0;
	}

}
