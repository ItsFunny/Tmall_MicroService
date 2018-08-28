/**
*
* @author joker 
* @date 创建时间：2018年6月24日 下午6:17:18
* 
*/
package com.tmall.server.product.dao.count;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
* 
* @author joker 
* @date 创建时间：2018年6月24日 下午6:17:18
*/
@Mapper
public interface PropertyCountDao
{
	Integer countPropertyInCatIds(List<Integer>categoryIds);

}
