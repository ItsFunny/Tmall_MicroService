/**
*
* @author joker 
* @date 创建时间：2018年9月7日 下午2:49:10
* 
*/
package com.tmall.server.auth.provider.service;

import java.util.List;
import java.util.Map;

import com.tmall.server.auth.common.model.TmallTemplate;


/**
* 
* @author joker 
* @date 创建时间：2018年9月7日 下午2:49:10
*/

public interface IConfigTemplateService
{
	String TYPE="type";
	
	String TEMPLATE_ID="templateId";
	
	
	List<TmallTemplate> findByType(Integer type);
	
	List<TmallTemplate>findByConditions(Map<String, Object>condition);
}
