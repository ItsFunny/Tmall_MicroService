/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:23:32
* 
*/
package com.tmall.server.product.dao.db0;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tmall.common.dto.PropertyDTO;

/**
* 
* @author joker 
* @date 创建时间：2018年6月24日 下午6:23:32
*/
@Mapper
public interface DB0PropertyDao
{
	Collection<PropertyDTO>findPropertiesInCatIds(List<Integer>catIds);

}
