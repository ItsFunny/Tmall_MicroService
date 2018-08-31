/**
*
* @author joker 
* @date 创建时间：2018年6月25日 下午1:24:59
* 
*/
package com.tmall.server.product.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月25日 下午1:24:59
 */
@Mapper
public interface PictureDao
{
	@Insert("insert into tmall_picture values (#{pictureid},#{pictureUrl})")
	void add(@Param("pictureId") String pictureId, @Param("pictureUrl") String pictureUrl);
}
