package com.tmall.server.store.provider.service;

import com.tmall.server.store.common.model.TmallCompany;

public interface ICompanyService
{
	
	TmallCompany findByCompnayId(Long companyId);
}
