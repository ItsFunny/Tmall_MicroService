/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午8:10:39
* 
*/
package com.tmall.server.store.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.server.store.common.model.TmallCompany;
import com.tmall.server.store.provider.dao.CompanyDao;
import com.tmall.server.store.provider.service.ICompanyService;


/**
* 
* @author joker 
* @date 创建时间：2018年8月28日 上午8:10:39
*/
@Service
public class CompanyServiceImpl implements ICompanyService
{
	@Autowired
	private CompanyDao companyDao;

	@Override
	public TmallCompany findByCompnayId(Long companyId)
	{
		return companyDao.selectByPrimaryKey(companyId);
	}
	
	

}
