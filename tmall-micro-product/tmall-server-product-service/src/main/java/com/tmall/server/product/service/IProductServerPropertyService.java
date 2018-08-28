/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:43:38
* 
*/
package com.tmall.server.product.service;

import java.util.Collection;
import java.util.List;

import com.tmall.common.dto.PropertyDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月24日 下午6:43:38
*/
public interface IProductServerPropertyService
{
	Collection<PropertyDTO>findPropertiesInCatIds(List<Integer>catIds);
}
