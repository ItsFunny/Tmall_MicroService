package com.tmall.server.auth.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallAction;
import com.tmall.server.auth.common.model.TmallActionExample;


public interface TmallActionDao {
    long countByExample(TmallActionExample example);

    int deleteByExample(TmallActionExample example);

    int deleteByPrimaryKey(Long actionId);

    int insert(TmallAction record);

    int insertSelective(TmallAction record);

    List<TmallAction> selectByExample(TmallActionExample example);

    TmallAction selectByPrimaryKey(Long actionId);

    int updateByExampleSelective(@Param("record") TmallAction record, @Param("example") TmallActionExample example);

    int updateByExample(@Param("record") TmallAction record, @Param("example") TmallActionExample example);

    int updateByPrimaryKeySelective(TmallAction record);

    int updateByPrimaryKey(TmallAction record);
}