/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午9:07:43
* 
*/
package com.rebuildtmall.tmall_batch.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.rebuildtmall.tmall_batch.model.UserOffsiteRecord;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午9:07:43
 */
@Mapper
public interface UserAbnormalDao
{

	@Insert("insert into user_offsite_login (id,user_id,ip,create_date) values (#{id},#{userId},#{ip},#{createDate})")
	void addRecord(UserOffsiteRecord record);
}
