package com.tmall.server.product.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.product.common.model.TmallStoreCategory;
import com.tmall.server.product.common.model.TmallStoreCategoryExample;

public interface TmallStoreCategoryDao {
    long countByExample(TmallStoreCategoryExample example);

    int deleteByExample(TmallStoreCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TmallStoreCategory record);

    int insertSelective(TmallStoreCategory record);

    List<TmallStoreCategory> selectByExample(TmallStoreCategoryExample example);

    TmallStoreCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TmallStoreCategory record, @Param("example") TmallStoreCategoryExample example);

    int updateByExample(@Param("record") TmallStoreCategory record, @Param("example") TmallStoreCategoryExample example);

    int updateByPrimaryKeySelective(TmallStoreCategory record);

    int updateByPrimaryKey(TmallStoreCategory record);
}