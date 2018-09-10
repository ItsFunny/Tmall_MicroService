/**
*
* @author joker 
* @date 创建时间：2018年8月4日 下午7:21:32
* 
*/
package com.tmall.server.store.provider.dao;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
* 
* @author joker 
* @date 创建时间：2018年8月4日 下午7:21:32
*/
@Mapper
public interface StoreDao extends TmallStoreDao
{
	@Select("select count(1) from tmall_store ")
	Long countStores();
}
