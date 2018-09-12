package com.tmall.server.auth.common.model;

import java.util.Date;

import com.tmall.common.dto.ActionDTO;


public class TmallAction {
    private Long actionId;

    private Integer actionVersion;

    private String actionUrl;

    private String actionName;

    private String actionValue;

    private Integer actionStatus;

    private String actionDescription;

    private Long menuId;

    private String createor;

    private Long createorId;

    private String lastOperator;

    private Long lastOperatorId;

    private Date createDate;

    private Date updateDate;
    
    public ActionDTO to()
    {
    	ActionDTO dto=new ActionDTO();
    	dto.setActionId(this.actionId);
    	dto.setActionVersion(this.actionVersion);
    	dto.setActionUrl(this.actionUrl);
    	dto.setActionName(this.actionName);
    	dto.setActionValue(this.actionValue);
    	dto.setActionStatus(this.actionStatus);
    	dto.setActionDescription(this.actionDescription);
    	dto.setMenuId(this.menuId);
    	dto.setCreateor(this.createor);
    	dto.setCreateorId(this.createorId);
    	dto.setLastOperator(this.lastOperator);
    	dto.setLastOperatorId(this.lastOperatorId);
    	dto.setCreateDate(this.createDate);
    	dto.setUpdateDate(this.updateDate);
    	return dto;
    }
    
    
    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Integer getActionVersion() {
        return actionVersion;
    }

    public void setActionVersion(Integer actionVersion) {
        this.actionVersion = actionVersion;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue == null ? null : actionValue.trim();
    }

    public Integer getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(Integer actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription == null ? null : actionDescription.trim();
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getCreateor() {
        return createor;
    }

    public void setCreateor(String createor) {
        this.createor = createor == null ? null : createor.trim();
    }

    public Long getCreateorId() {
        return createorId;
    }

    public void setCreateorId(Long createorId) {
        this.createorId = createorId;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator == null ? null : lastOperator.trim();
    }

    public Long getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Long lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}