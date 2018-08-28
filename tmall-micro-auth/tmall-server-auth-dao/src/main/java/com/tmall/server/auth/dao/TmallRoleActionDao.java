package com.tmall.server.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallRoleAction;
import com.tmall.server.auth.common.model.TmallRoleActionExample;

@Mapper
public interface TmallRoleActionDao {
    long countByExample(TmallRoleActionExample example);

    int deleteByExample(TmallRoleActionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TmallRoleAction record);

    int insertSelective(TmallRoleAction record);

    List<TmallRoleAction> selectByExample(TmallRoleActionExample example);

    TmallRoleAction selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TmallRoleAction record, @Param("example") TmallRoleActionExample example);

    int updateByExample(@Param("record") TmallRoleAction record, @Param("example") TmallRoleActionExample example);

    int updateByPrimaryKeySelective(TmallRoleAction record);

    int updateByPrimaryKey(TmallRoleAction record);
}