/**
*
* @author joker 
* @date 创建时间：2018年9月5日 上午10:46:58
* 
*/
package com.tmall.server.product.dao.db1;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.core.annotation.Order;

import com.tmall.common.model.MessageModel;
import com.tmall.server.product.dao.MessageDao;



/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 上午10:46:58
 */
@Mapper
@Order(1)
public interface DB1MessageDao extends MessageDao
{
	@Select("select * from message where message_id=#{id}")
	MessageModel findById(String id);

	@Insert("insert into ${tableName} values (#{messageId},#{messageDetail},#{messageStatus})")
	int insert(MessageModel messageModel);
	
	@Delete("delete from ${tableName} where message_id =#{id} ")
	Integer deleteById(String id);
	
}
