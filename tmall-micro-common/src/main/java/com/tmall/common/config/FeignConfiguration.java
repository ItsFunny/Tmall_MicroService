/**
*
* @author joker 
* @date 创建时间：2018年8月18日 下午6:10:35
* 
*/
package com.tmall.common.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tmall.common.exception.TmallErrorDecoder;

/**
* 
* @author joker 
* @date 创建时间：2018年8月18日 下午6:10:35
*/
@Configuration
public class FeignConfiguration
{
	@Bean
	public TmallErrorDecoder errorDecoder()
	{
		return new TmallErrorDecoder();
	}

}
