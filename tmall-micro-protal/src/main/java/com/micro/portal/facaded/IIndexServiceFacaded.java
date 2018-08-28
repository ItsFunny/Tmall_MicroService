/**
*
* @author joker 
* @date 创建时间：2018年5月27日 上午11:34:51
* 
*/
package com.micro.portal.facaded;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.micro.portal.model.PortalCategory;
import com.micro.product.common.dto.CategoryDTO;

/**
* 门面:整合其他子服务的系统
* @author joker 
* @date 创建时间：2018年5月27日 上午11:34:51
*/
public interface IIndexServiceFacaded
{
	/*
	 * 显示首页左边的类目栏
	 */
	Collection<PortalCategory>showCategories();
}
