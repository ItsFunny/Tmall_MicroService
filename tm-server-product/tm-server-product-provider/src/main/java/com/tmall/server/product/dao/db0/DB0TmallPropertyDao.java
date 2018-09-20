package com.tmall.server.product.dao.db0;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tmall.server.product.common.model.TmallProperty;
import com.tmall.server.product.common.model.TmallPropertyExample;
import com.tmall.server.product.dao.TmallPropertyDao;

@Component(value = "db0TmallPropertyDao")
@Mapper
@Order(0)
public interface DB0TmallPropertyDao extends TmallPropertyDao
{
	long countByExample(TmallPropertyExample example);

	int deleteByExample(TmallPropertyExample example);

	int deleteByPrimaryKey(Integer propertyId);

	int insert(TmallProperty record);

	int insertSelective(TmallProperty record);

	List<TmallProperty> selectByExample(TmallPropertyExample example);

	TmallProperty selectByPrimaryKey(Integer propertyId);

	int updateByExampleSelective(@Param("record") TmallProperty record, @Param("example") TmallPropertyExample example);

	int updateByExample(@Param("record") TmallProperty record, @Param("example") TmallPropertyExample example);

	int updateByPrimaryKeySelective(TmallProperty record);

	int updateByPrimaryKey(TmallProperty record);
}