package com.tmall.server.product.common.model;

import com.tmall.common.dto.BrandDTO;

public class TmallStoreBrand {
    private Long id;

    private Long storeId;

    private Integer brandId;

    private String brandName;

    public BrandDTO to()
    {
    	BrandDTO brandDTO=new BrandDTO();
    	brandDTO.setBrandId(this.brandId);
    	brandDTO.setStoreId(this.storeId);
    	brandDTO.setBrandName(this.brandName);
    	return brandDTO;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }
}