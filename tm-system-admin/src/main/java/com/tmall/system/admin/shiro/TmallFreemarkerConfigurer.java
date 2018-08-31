/**
*
* @author joker 
* @date 创建时间：2018年8月17日 下午4:15:56
* 
*/
package com.tmall.system.admin.shiro;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/**
* 
* @author joker 
* @date 创建时间：2018年8月17日 下午4:15:56
*/
public class TmallFreemarkerConfigurer extends FreeMarkerConfigurer
{
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException
	{
		// TODO Auto-generated method stub
		super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
	

}
