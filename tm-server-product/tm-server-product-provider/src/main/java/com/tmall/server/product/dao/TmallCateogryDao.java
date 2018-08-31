package com.tmall.server.product.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.common.model.TmallCategoryExample;



public interface TmallCateogryDao {
    long countByExample(TmallCategoryExample example);

    int deleteByExample(TmallCategoryExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(TmallCategory record);

    int insertSelective(TmallCategory record);

    List<TmallCategory> selectByExample(TmallCategoryExample example);

    TmallCategory selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") TmallCategory record, @Param("example") TmallCategoryExample example);

    int updateByExample(@Param("record") TmallCategory record, @Param("example") TmallCategoryExample example);

    int updateByPrimaryKeySelective(TmallCategory record);

    int updateByPrimaryKey(TmallCategory record);
}