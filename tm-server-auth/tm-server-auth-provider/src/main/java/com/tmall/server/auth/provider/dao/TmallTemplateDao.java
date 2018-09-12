package com.tmall.server.auth.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.auth.common.model.TmallTemplate;
import com.tmall.server.auth.common.model.TmallTemplateExample;

public interface TmallTemplateDao {
    long countByExample(TmallTemplateExample example);

    int deleteByExample(TmallTemplateExample example);

    int insert(TmallTemplate record);

    int insertSelective(TmallTemplate record);

    List<TmallTemplate> selectByExample(TmallTemplateExample example);

    int updateByExampleSelective(@Param("record") TmallTemplate record, @Param("example") TmallTemplateExample example);

    int updateByExample(@Param("record") TmallTemplate record, @Param("example") TmallTemplateExample example);
}