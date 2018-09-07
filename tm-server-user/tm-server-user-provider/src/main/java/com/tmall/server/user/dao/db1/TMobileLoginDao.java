package com.tmall.server.user.dao.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.user.common.model.TmallMobileLogin;
import com.tmall.server.user.common.model.TmallMobileLoginExample;




@Mapper
public interface TMobileLoginDao {
    long countByExample(TmallMobileLoginExample example);

    int deleteByExample(TmallMobileLoginExample example);

    int insert(TmallMobileLogin record);

    int insertSelective(TmallMobileLogin record);

    List<TmallMobileLogin> selectByExample(TmallMobileLoginExample example);

    int updateByExampleSelective(@Param("record") TmallMobileLogin record, @Param("example") TmallMobileLoginExample example);

    int updateByExample(@Param("record") TmallMobileLogin record, @Param("example") TmallMobileLoginExample example);
}