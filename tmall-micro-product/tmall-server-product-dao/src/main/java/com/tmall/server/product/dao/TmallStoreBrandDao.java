package com.tmall.server.product.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.product.common.model.TmallStoreBrand;
import com.tmall.server.product.common.model.TmallStoreBrandExample;

public interface TmallStoreBrandDao {
    long countByExample(TmallStoreBrandExample example);

    int deleteByExample(TmallStoreBrandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TmallStoreBrand record);

    int insertSelective(TmallStoreBrand record);

    List<TmallStoreBrand> selectByExample(TmallStoreBrandExample example);

    TmallStoreBrand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TmallStoreBrand record, @Param("example") TmallStoreBrandExample example);

    int updateByExample(@Param("record") TmallStoreBrand record, @Param("example") TmallStoreBrandExample example);

    int updateByPrimaryKeySelective(TmallStoreBrand record);

    int updateByPrimaryKey(TmallStoreBrand record);
}