package com.tmall.server.store.common.model;

import com.tmall.common.dto.CompanyDTO;

public class TmallCompany {
    private Long companyId;

    private String companyName;

    private String companyPhone;

    private String companyMobilePhone;

    private String companyAddress;

    private String userId;

    private String companyFounderr;
    
    public CompanyDTO to()
    {
    	CompanyDTO companyDTO=new CompanyDTO();
    	companyDTO.setCompanyId(this.companyId);
    	companyDTO.setCompanyName(this.companyName);
    	companyDTO.setCompanyPhone(this.companyPhone);
    	companyDTO.setCompanyAddress(this.companyAddress);
    	companyDTO.setCompanyMobilePhone(this.companyMobilePhone);
    	companyDTO.setUserId(this.userId);
    	companyDTO.setCompanyFounderr(this.companyFounderr);
    	return companyDTO;
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

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getCompanyMobilePhone() {
        return companyMobilePhone;
    }

    public void setCompanyMobilePhone(String companyMobilePhone) {
        this.companyMobilePhone = companyMobilePhone == null ? null : companyMobilePhone.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCompanyFounderr() {
        return companyFounderr;
    }

    public void setCompanyFounderr(String companyFounderr) {
        this.companyFounderr = companyFounderr == null ? null : companyFounderr.trim();
    }
}