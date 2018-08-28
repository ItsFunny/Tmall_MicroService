package com.tmall.server.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.user.common.model.TmallUser;
import com.tmall.server.user.common.model.TmallUserExample;

@Mapper
public interface TUserDao {
    long countByExample(TmallUserExample example);

    int deleteByExample(TmallUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(TmallUser record);

    int insertSelective(TmallUser record);

    List<TmallUser> selectByExample(TmallUserExample example);

    TmallUser selectByPrimaryKey(@Param("userId")Long userId,@Param("tableNum")Integer tableNum);

    int updateByExampleSelective(@Param("record") TmallUser record, @Param("example") TmallUserExample example);

    int updateByExample(@Param("record") TmallUser record, @Param("example") TmallUserExample example);

    int updateByPrimaryKeySelective(TmallUser record);

    int updateByPrimaryKey(TmallUser record);
}