package com.tmall.server.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallRole;
import com.tmall.server.auth.common.model.TmallRoleExample;

public interface TmallRoleDao {
    long countByExample(TmallRoleExample example);

    int deleteByExample(TmallRoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(TmallRole record);

    int insertSelective(TmallRole record);

    List<TmallRole> selectByExample(TmallRoleExample example);

    TmallRole selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") TmallRole record, @Param("example") TmallRoleExample example);

    int updateByExample(@Param("record") TmallRole record, @Param("example") TmallRoleExample example);

    int updateByPrimaryKeySelective(TmallRole record);

    int updateByPrimaryKey(TmallRole record);
}