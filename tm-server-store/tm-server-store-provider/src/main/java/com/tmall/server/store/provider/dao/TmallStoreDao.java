package com.tmall.server.store.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.store.common.model.TmallStore;
import com.tmall.server.store.common.model.TmallStoreExample;


public interface TmallStoreDao {
	
	//自定义 
	//这条sql可变
	Long countStoresByExample(TmallStoreExample example);
	
	
	//mbg
    long countByExample(TmallStoreExample example);

    int deleteByExample(TmallStoreExample example);

    int deleteByPrimaryKey(Long storeId);

    int insert(TmallStore record);

    int insertSelective(TmallStore record);

    List<TmallStore> selectByExample(TmallStoreExample example);

    TmallStore selectByPrimaryKey(Long storeId);

    int updateByExampleSelective(@Param("record") TmallStore record, @Param("example") TmallStoreExample example);

    int updateByExample(@Param("record") TmallStore record, @Param("example") TmallStoreExample example);

    int updateByPrimaryKeySelective(TmallStore record);

    int updateByPrimaryKey(TmallStore record);
}