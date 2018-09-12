package com.tmall.server.auth.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallRoleMenu;
import com.tmall.server.auth.common.model.TmallRoleMenuExample;


@Mapper
public interface TmallRoleMenuDao {
    long countByExample(TmallRoleMenuExample example);

    int deleteByExample(TmallRoleMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TmallRoleMenu record);

    int insertSelective(TmallRoleMenu record);

    List<TmallRoleMenu> selectByExample(TmallRoleMenuExample example);

    TmallRoleMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TmallRoleMenu record, @Param("example") TmallRoleMenuExample example);

    int updateByExample(@Param("record") TmallRoleMenu record, @Param("example") TmallRoleMenuExample example);

    int updateByPrimaryKeySelective(TmallRoleMenu record);

    int updateByPrimaryKey(TmallRoleMenu record);
}