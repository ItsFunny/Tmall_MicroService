/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月26日 下午4:20:03
* 
*/
package com.tmall.system.admin.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月26日 下午4:20:03
*/
public class TmallPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{

	@Override
	protected String convertProperty(String propertyName, String propertyValue)
	{
		if(propertyName.equals("tmall.config.password"))
		{
			System.out.println("password");
		}
		return super.convertProperty(propertyName, propertyValue);
	}
	public static float fun(int n,int max)
	{
		float param=(1.0f)/n;
//		if(n==1)
//		{
//			return 1;
//		}else if(n%2!=0)
//		{
//			param*=-1;
//		}
//		return param+fun(n-1);
		if(n==1)
		{
			param=1;
		}else if(n%2!=0)
		{
			param*=-1;
		}
		if(n==max)
		{
			return param;
		}else {
			return param+=fun(n+1, max);
		}
		
	}
	public static Integer sum(Integer n)
	{
		for(int i=0;i<n;i++)
		{
			
		}
		return 0;
	}
	public static void main(String[] args)
	{
		System.out.println(fun(1,10));
	}
}
