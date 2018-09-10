package com.tmall.server.store.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.store.common.model.TmallStoreBrand;
import com.tmall.server.store.common.model.TmallStoreBrandExample;

@Mapper
public interface TmallStoreBrandDao {
	
    long countByExample(TmallStoreBrandExample example);

    int deleteByExample(TmallStoreBrandExample example);

    int insert(TmallStoreBrand record);

    int insertSelective(TmallStoreBrand record);

    List<TmallStoreBrand> selectByExample(TmallStoreBrandExample example);

    int updateByExampleSelective(@Param("record") TmallStoreBrand record, @Param("example") TmallStoreBrandExample example);

    int updateByExample(@Param("record") TmallStoreBrand record, @Param("example") TmallStoreBrandExample example);
}