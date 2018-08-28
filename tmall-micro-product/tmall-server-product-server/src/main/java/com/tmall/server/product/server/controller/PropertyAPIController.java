/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:16:38
* 
*/
package com.tmall.server.product.server.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.reflect.TypeToken;
import com.tmall.common.constants.TmallURLConstant;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.ResultDTO;
import com.tmall.common.utils.JsonUtils;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.product.service.IProductServerPropertyService;

import net.sf.json.JSONArray;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月24日 下午6:16:38
 */
@RestController
@CrossOrigin(value =
{ TmallURLConstant.TMALL_MANAGEMENT_SYSTEM })
@RequestMapping("/property")
public class PropertyAPIController
{
	@Autowired
	private IProductServerPropertyService propertyService;


	@RequestMapping(value = "/names", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<Collection<PropertyDTO>> findPropertyNames(HttpServletRequest request,
			HttpServletResponse response)
	{
		String ids = request.getParameter("ids");
		
		if (null == ids || StringUtils.isEmpty(ids))
		{
			return ResultUtils.fail("illegal argument,参数错误");
		}
		String replaceAll = ids.replaceAll("\"", "");
		String[] strings = replaceAll.split(",");
		List<Integer>categoryIds=new ArrayList<Integer>();
		for (String string : strings)
		{
			categoryIds.add(Integer.parseInt(string));
		}
		Collection<PropertyDTO> propertiesInCatIds = propertyService.findPropertiesInCatIds(categoryIds);
		return ResultUtils.sucess(propertiesInCatIds);
	}
}
