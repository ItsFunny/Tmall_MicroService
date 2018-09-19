/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月19日 下午8:25:05
* 
*/
package com.tmall.internal.spi.service;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.UserRequestDTO;

/**
* 
* @When
* @Description
* @Detail
* @author joker 
* @date 创建时间：2018年9月19日 下午8:25:05
*/
public interface IInternalCategoryServiece
{
	ResultDTO<?>updateCategoryStatus(UserRequestDTO dto);
}
