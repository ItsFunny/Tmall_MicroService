package com.tmall.server.store.provider.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tmall.server.store.common.model.TmallCompany;
import com.tmall.server.store.common.model.TmallCompanyExample;


public interface TmallCompanyDao {
    long countByExample(TmallCompanyExample example);

    int deleteByExample(TmallCompanyExample example);

    int deleteByPrimaryKey(Long companyId);

    int insert(TmallCompany record);

    int insertSelective(TmallCompany record);

    List<TmallCompany> selectByExample(TmallCompanyExample example);

    TmallCompany selectByPrimaryKey(Long companyId);

    int updateByExampleSelective(@Param("record") TmallCompany record, @Param("example") TmallCompanyExample example);

    int updateByExample(@Param("record") TmallCompany record, @Param("example") TmallCompanyExample example);

    int updateByPrimaryKeySelective(TmallCompany record);

    int updateByPrimaryKey(TmallCompany record);
}