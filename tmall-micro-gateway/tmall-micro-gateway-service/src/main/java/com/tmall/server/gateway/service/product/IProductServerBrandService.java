package com.tmall.server.gateway.service.product;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;

import com.tmall.common.dto.BrandDTO;
import com.tmall.common.dto.ResultDTO;

public interface IProductServerBrandService
{
	ResultDTO<Collection<BrandDTO>>findAllBrands();
	
}
