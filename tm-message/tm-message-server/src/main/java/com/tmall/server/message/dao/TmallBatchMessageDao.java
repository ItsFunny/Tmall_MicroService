package com.tmall.server.message.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tm.message.common.model.TmallBatchMessage;
import com.tm.message.common.model.TmallBatchMessageExample;


public interface TmallBatchMessageDao {
    long countByExample(TmallBatchMessageExample example);

    int deleteByExample(TmallBatchMessageExample example);

    int insert(TmallBatchMessage record);

    int insertSelective(TmallBatchMessage record);

    List<TmallBatchMessage> selectByExample(TmallBatchMessageExample example);

    int updateByExampleSelective(@Param("record") TmallBatchMessage record, @Param("example") TmallBatchMessageExample example);

    int updateByExample(@Param("record") TmallBatchMessage record, @Param("example") TmallBatchMessageExample example);
}