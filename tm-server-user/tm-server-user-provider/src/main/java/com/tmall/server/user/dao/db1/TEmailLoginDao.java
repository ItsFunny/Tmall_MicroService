package com.tmall.server.user.dao.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.user.common.model.TmallEmailLogin;
import com.tmall.server.user.common.model.TmallEmailLoginExample;




@Mapper
public interface TEmailLoginDao {
    long countByExample(TmallEmailLoginExample example);

    int deleteByExample(TmallEmailLoginExample example);

    int insert(TmallEmailLogin record);

    int insertSelective(TmallEmailLogin record);

    List<TmallEmailLogin> selectByExample(TmallEmailLoginExample example);

    int updateByExampleSelective(@Param("record") TmallEmailLogin record, @Param("example") TmallEmailLoginExample example);

    int updateByExample(@Param("record") TmallEmailLogin record, @Param("example") TmallEmailLoginExample example);
}