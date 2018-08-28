package com.tmall.server.auth.common.model;

import com.tmall.common.dto.RoleDTO;

public class TmallRole {
    private Long roleId;

    private Long storeId;

    private Integer roleStatus;

    private String roleName;

    private String roleDescription;
    
    public void to(RoleDTO roleDTO)
    {
    	roleDTO.setRoleId(this.roleId);
    	roleDTO.setRoleStatus(this.roleStatus);
    	roleDTO.setStoreId(this.storeId);
    	roleDTO.setRoleName(this.roleName);
    	roleDTO.setRoleDescription(this.roleDescription);
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }

	public Integer getRoleStatus()
	{
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus)
	{
		this.roleStatus = roleStatus;
	}
}