package com.tmall.server.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallUserRole;
import com.tmall.server.auth.common.model.TmallUserRoleExample;

@Mapper
public interface TmallUserRoleDao {
    long countByExample(TmallUserRoleExample example);

    int deleteByExample(TmallUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TmallUserRole record);

    int insertSelective(TmallUserRole record);

    List<TmallUserRole> selectByExample(TmallUserRoleExample example);

    TmallUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TmallUserRole record, @Param("example") TmallUserRoleExample example);

    int updateByExample(@Param("record") TmallUserRole record, @Param("example") TmallUserRoleExample example);

    int updateByPrimaryKeySelective(TmallUserRole record);

    int updateByPrimaryKey(TmallUserRole record);
}