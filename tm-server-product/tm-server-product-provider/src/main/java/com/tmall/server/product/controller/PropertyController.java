/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午4:34:11
* 
*/
package com.tmall.server.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joker.library.dto.ResultDTO;
import com.joker.library.page.PageRequestDTO;
import com.joker.library.page.PageResponseDTO;
import com.joker.library.service.IdWorkerService;
import com.tmall.common.annotation.ArgumentCheckAnnotation;
import com.tmall.common.constants.SQLExtentionConstant;
import com.tmall.common.constants.UserRequestConstant;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.dto.PropertyDTO.PropertyValueDTO;
import com.tmall.common.mq.UserRecordFactory;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.wrapper.UserRecordAspectWrapper;
import com.tmall.server.product.service.IPropertyService;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月20日 下午4:34:11
 */
@RestController
@RequestMapping(value = "/auth/property")
public class PropertyController
{
	@Autowired
	private IPropertyService propertyService;
	@Autowired
	private IdWorkerService idWorkService;

	@ArgumentCheckAnnotation(mapKey = UserRequestConstant.PRODUCT_PROPERTY, parseType = PropertyDTO.class)
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?> addPropertyAndValue(@RequestBody UserRequestDTO userRequestDTO)
	{
		Map<String, Object> props = userRequestDTO.getExtProps();
		Object obj = props.get(UserRequestConstant.PRODUCT_PROPERTY);
		ObjectMapper mapper = new ObjectMapper();
		PropertyDTO propertyDTO = mapper.convertValue(obj, PropertyDTO.class);
		Integer id = propertyDTO.getPropertyId();
		String detail = "";
		if (null == id)
		{
			detail = "添加" + propertyDTO.getPropertyName() + "属性";
			// 添加
			id = ((Number) idWorkService.nextId()).intValue();
			if (id < 0)
			{
				id *= -1;
			}
			propertyDTO.setPropertyId(id);
			for (PropertyValueDTO valueDTO : propertyDTO.getValues())
			{
				valueDTO.setPropertyId(id);
			}
		} else
		{
			// 更新
			detail = "更新id为:" + id + ",属性为:" + propertyDTO.getPropertyName() + "的属性";
		}

		UserRecordAspectWrapper<PropertyDTO> wrapper = UserRecordFactory.create(userRequestDTO.getUser(), detail,
				propertyDTO);
		return propertyService.addPropertyAndValue(wrapper);
	}

	@PostMapping(value = "/show", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PageResponseDTO<List<PropertyDTO>>> showProperties(@RequestBody PageRequestDTO pageRequestDTO)
	{
		PageResponseDTO<List<PropertyDTO>> pageResponseDTO = propertyService.findByCondition(pageRequestDTO);
		return ResultUtils.sucess(pageResponseDTO);
	}

	@GetMapping(value = "/{propertyId}/values", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<PropertyDTO> showPropertyValues(@PathVariable("propertyId") Integer propertyId)
	{
		return propertyService.showPropertyValues(propertyId);
	}

	// 这个跟上面的addProperty有重复好像
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?> updateProperty(@RequestBody UserRequestDTO userRequestDTO)
	{
		Map<String, Object> props = userRequestDTO.getExtProps();
		Object object = props.get(UserRequestConstant.PRODUCT_PROPERTY);
		ObjectMapper mapper = new ObjectMapper();
		PropertyDTO propertyDTO = mapper.convertValue(object, PropertyDTO.class);
		String detail = "更新属性,更新属性id为:" + propertyDTO.getPropertyId() + "名为:" + propertyDTO.getPropertyName() + "的属性";
		UserRecordAspectWrapper<PropertyDTO> wrapper = UserRecordFactory.create(userRequestDTO.getUser(), detail,
				propertyDTO);
		Object object2 = props.get(UserRequestConstant.PRODUCT_PROPERTY_DELETEIDS);
		List<Long>deleteIds = null;
		if(null!=object2)
		{
			deleteIds=mapper.convertValue(object2, List.class);
		}
		return propertyService.updateProperty(wrapper,deleteIds);
	}

}
