/**
*
* @Description
* @author joker 
* @date 创建时间：2018年7月29日 下午10:45:32
* 
*/
package com.tmall.common.spring;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年7月29日 下午10:45:32
*/
public class TmallPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{

	@Override
	protected String convertProperty(String propertyName, String propertyValue)
	{
		return super.convertProperty(propertyName, propertyValue);
	}
	

}
