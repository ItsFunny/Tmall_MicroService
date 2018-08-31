/**
*
* @author joker 
* @date 创建时间：2018年6月23日 上午11:03:39
* 
*/
package com.tmall.server.product.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tmall.common.dto.BrandDTO;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月23日 上午11:03:39
 */
@Mapper
public interface BrandDao extends TmallStoreBrandDao
{
	@Select("select b.brand_id,b.picture_id,b.brand_name,b.brand_desc,b.brand_type_id,t.brand_type_name,p.picture_url from tmall_brand b "
			+ "left join tmall_brand_type t on b.brand_type_id=t.brand_type_id "
			+ "left join tmall_picture p on p.picture_id=b.picture_id")
	Collection<BrandDTO> findAll();

}
