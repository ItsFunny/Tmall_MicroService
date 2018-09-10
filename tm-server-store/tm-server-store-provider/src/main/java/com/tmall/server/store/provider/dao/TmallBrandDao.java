package com.tmall.server.store.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.store.common.model.TmallBrand;
import com.tmall.server.store.common.model.TmallBrandExample;

public interface TmallBrandDao {
    long countByExample(TmallBrandExample example);

    int deleteByExample(TmallBrandExample example);

    int insert(TmallBrand record);

    int insertSelective(TmallBrand record);

    List<TmallBrand> selectByExample(TmallBrandExample example);

    int updateByExampleSelective(@Param("record") TmallBrand record, @Param("example") TmallBrandExample example);

    int updateByExample(@Param("record") TmallBrand record, @Param("example") TmallBrandExample example);
}