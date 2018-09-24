/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月23日 下午8:27:30
* 
*/
package com.tmall.internal.spi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.internal.spi.service.IInternalPropertyService;
import com.tmall.server.spi.open.product.IInternalProductServerPropertyService;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月23日 下午8:27:30
*/
@Service
public class IInternalPropertyServiceImpl implements IInternalPropertyService
{
	@Autowired
	private IInternalProductServerPropertyService propertyService;

	public ResultDTO<PropertyDTO> findPropertyValues(Integer propertyId)
	{
		return propertyService.findPropertyValues(propertyId);
	}

	public ResultDTO<?> updateProperty(UserRequestDTO userRequestDTO)
	{
		return propertyService.updateProperty(userRequestDTO);
	}

}
