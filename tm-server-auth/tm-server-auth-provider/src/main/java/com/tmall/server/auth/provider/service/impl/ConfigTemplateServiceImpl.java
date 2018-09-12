/**
*
* @author joker 
* @date 创建时间：2018年9月7日 下午2:49:37
* 
*/
package com.tmall.server.auth.provider.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.server.auth.common.model.TmallTemplate;
import com.tmall.server.auth.common.model.TmallTemplateExample;
import com.tmall.server.auth.common.model.TmallTemplateExample.Criteria;
import com.tmall.server.auth.provider.dao.TmallConfigTemplateDao;
import com.tmall.server.auth.provider.service.IConfigTemplateService;


/**
 * 
 * @author joker
 * @date 创建时间：2018年9月7日 下午2:49:37
 */
@Service
public class ConfigTemplateServiceImpl implements IConfigTemplateService
{
	@Autowired
	private TmallConfigTemplateDao configTemplateDao;

	@Override
	public List<TmallTemplate> findByType(Integer type)
	{
		TmallTemplateExample example = new TmallTemplateExample();
		Criteria criteria = example.createCriteria();
		criteria.andTemplateTypeEqualTo(type);
		List<TmallTemplate> templates = configTemplateDao.selectByExample(example);
		if (null == templates)
		{
			return null;
		} else
		{
			return templates;
		}
	}

	@Override
	public List<TmallTemplate> findByConditions(Map<String, Object> condition)
	{
		TmallTemplateExample example=new TmallTemplateExample();
		Criteria criteria = example.createCriteria();
		if(condition.containsKey(IConfigTemplateService.TYPE))
		{
			example.or().andTemplateTypeEqualTo(Integer.parseInt(condition.get(IConfigTemplateService.TYPE).toString()));
		}
		return configTemplateDao.selectByExample(example);
	}

}
