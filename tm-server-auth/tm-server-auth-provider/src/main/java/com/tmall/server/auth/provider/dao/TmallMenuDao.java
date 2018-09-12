package com.tmall.server.auth.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallMenu;
import com.tmall.server.auth.common.model.TmallMenuExample;


public interface TmallMenuDao {
    long countByExample(TmallMenuExample example);

    int deleteByExample(TmallMenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(TmallMenu record);

    int insertSelective(TmallMenu record);

    List<TmallMenu> selectByExample(TmallMenuExample example);

    TmallMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") TmallMenu record, @Param("example") TmallMenuExample example);

    int updateByExample(@Param("record") TmallMenu record, @Param("example") TmallMenuExample example);

    int updateByPrimaryKeySelective(TmallMenu record);

    int updateByPrimaryKey(TmallMenu record);
}