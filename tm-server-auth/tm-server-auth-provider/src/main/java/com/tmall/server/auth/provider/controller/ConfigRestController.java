/**
*
* @author joker 
* @date 创建时间：2018年9月7日 下午2:59:10
* 
*/
package com.tmall.server.auth.provider.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.joker.library.utils.ResultUtils;
import com.tmall.common.dto.TmallConfigTemplateDTO;
import com.tmall.server.auth.common.model.TmallTemplate;
import com.tmall.server.auth.provider.service.IConfigTemplateService;


/**
 * 
 * @author joker
 * @date 创建时间：2018年9月7日 下午2:59:10
 */
@RestController
public class ConfigRestController
{
	@Autowired
	private IConfigTemplateService configTemplateService;

	@GetMapping(value = "/auth/config/template", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<List<TmallConfigTemplateDTO>> getConfigTemplate(
			@RequestParam("map") Map<String, Object> conditions)
	{
		List<TmallTemplate> templates = configTemplateService.findByConditions(conditions);
		List<TmallConfigTemplateDTO> configTemplateDTOs = new ArrayList<TmallConfigTemplateDTO>();
		if (null != configTemplateDTOs)
		{
			templates.forEach(t -> {
				configTemplateDTOs.add(t.to());
			});
		}
		return ResultUtils.sucess(configTemplateDTOs);

	}

}
