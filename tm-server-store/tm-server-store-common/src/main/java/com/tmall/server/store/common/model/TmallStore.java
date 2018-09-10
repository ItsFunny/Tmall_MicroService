package com.tmall.server.store.common.model;

import com.tmall.common.dto.StoreDTO;

public class TmallStore {
    private Long storeId;

    private String storeName;

    private Integer storeStatus;

    private String storeAbbName;

    private String storeDescription;

    private String storeContactPhone;

    private Long userId;

    private String storeContactName;

    private Long companyId;

    private String companyName;

    
    public StoreDTO to()
    {
    	StoreDTO storeDTO=new StoreDTO();
    	storeDTO.setStoreId(this.storeId);
    	storeDTO.setStoreName(this.storeName);
    	storeDTO.setStoreAbbName(this.storeAbbName);
    	storeDTO.setStoreDescription(this.storeDescription);
    	storeDTO.setStoreContactPhone(this.storeContactPhone);
    	storeDTO.setUserId(this.userId);
    	storeDTO.setStoreContactName(this.storeContactName);
    	storeDTO.setCompanyId(this.companyId);
    	storeDTO.setCompanyName(this.companyName);
    	storeDTO.setStoreStatus(this.storeStatus);
    	return storeDTO;
    	
    }
    
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getStoreAbbName() {
        return storeAbbName;
    }

    public void setStoreAbbName(String storeAbbName) {
        this.storeAbbName = storeAbbName == null ? null : storeAbbName.trim();
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription == null ? null : storeDescription.trim();
    }

    public String getStoreContactPhone() {
        return storeContactPhone;
    }

    public void setStoreContactPhone(String storeContactPhone) {
        this.storeContactPhone = storeContactPhone == null ? null : storeContactPhone.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStoreContactName() {
        return storeContactName;
    }

    public void setStoreContactName(String storeContactName) {
        this.storeContactName = storeContactName == null ? null : storeContactName.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}