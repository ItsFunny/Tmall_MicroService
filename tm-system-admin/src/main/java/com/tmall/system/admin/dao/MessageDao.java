/**
*
* @author joker 
* @date 创建时间：2018年9月5日 上午10:46:58
* 
*/
package com.tmall.system.admin.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tmall.common.model.MessageModel;


/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 上午10:46:58
 */
@Mapper
public interface MessageDao
{
	@Select("select * from message where id=#{id}")
	MessageModel findById(String id);

	@Insert("insert into message values (#{id},#{detail})")
	Integer insert(MessageModel messageModel);
	
	@Delete("delete from message where id =#{id} ")
	Integer deleteById(String id);
	
}
