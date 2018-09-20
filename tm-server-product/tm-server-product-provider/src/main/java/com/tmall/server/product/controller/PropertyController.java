/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午4:34:11
* 
*/
package com.tmall.server.product.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joker.library.dto.ResultDTO;
import com.tmall.common.constants.UserRequestConstant;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.common.mq.UserRecordFactory;
import com.tmall.common.utils.ResultUtils;
import com.tmall.common.wrapper.UserRecordAspectWrapper;

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
	@PostMapping(value="/add",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDTO<?>addPropertyAndValue(@RequestBody UserRequestDTO userRequestDTO)
	{
		Map<String, Object> props = userRequestDTO.getExtProps();
		Object obj = props.get(UserRequestConstant.PRODUCT_PROPERTY);
		ObjectMapper mapper=new ObjectMapper();
		PropertyDTO propertyDTO = mapper.convertValue(obj, PropertyDTO.class);
		String detail="添加"+propertyDTO.getPropertyName()+"属性";
		UserRecordAspectWrapper<PropertyDTO> wrapper = UserRecordFactory.create(userRequestDTO.getUser(), detail, propertyDTO);
		
		
		return ResultUtils.fail();
	}

}
