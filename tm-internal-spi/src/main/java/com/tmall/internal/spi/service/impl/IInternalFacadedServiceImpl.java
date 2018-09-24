/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月23日 下午8:29:42
* 
*/
package com.tmall.internal.spi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.PropertyDTO;
import com.tmall.common.dto.UserRequestDTO;
import com.tmall.internal.spi.service.IInternalFacadedService;
import com.tmall.internal.spi.service.IInternalPropertyService;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月23日 下午8:29:42
*/
@Service
public class IInternalFacadedServiceImpl implements IInternalFacadedService
{
	@Autowired
	private IInternalPropertyService iInternalPropertyService;

	public ResultDTO<PropertyDTO> findPropertyValues(Integer propertyId)
	{
		return iInternalPropertyService.findPropertyValues(propertyId);
	}

	public ResultDTO<?> updateProperty(UserRequestDTO userRequestDTO)
	{
		return iInternalPropertyService.updateProperty(userRequestDTO);
	}

}
