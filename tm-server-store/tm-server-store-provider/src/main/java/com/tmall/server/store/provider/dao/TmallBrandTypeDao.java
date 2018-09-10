package com.tmall.server.store.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.store.common.model.TmallBrandType;
import com.tmall.server.store.common.model.TmallBrandTypeExample;

@Mapper
public interface TmallBrandTypeDao {
    long countByExample(TmallBrandTypeExample example);

    int deleteByExample(TmallBrandTypeExample example);

    int deleteByPrimaryKey(Integer brandTypeId);

    int insert(TmallBrandType record);

    int insertSelective(TmallBrandType record);

    List<TmallBrandType> selectByExample(TmallBrandTypeExample example);

    TmallBrandType selectByPrimaryKey(Integer brandTypeId);

    int updateByExampleSelective(@Param("record") TmallBrandType record, @Param("example") TmallBrandTypeExample example);

    int updateByExample(@Param("record") TmallBrandType record, @Param("example") TmallBrandTypeExample example);

    int updateByPrimaryKeySelective(TmallBrandType record);

    int updateByPrimaryKey(TmallBrandType record);
}