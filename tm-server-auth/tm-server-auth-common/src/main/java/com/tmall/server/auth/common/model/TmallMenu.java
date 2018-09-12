package com.tmall.server.auth.common.model;

import java.util.Date;

import com.tmall.common.dto.MenuDTO;

public class TmallMenu {
    private Long menuId;

    private String menuName;

    private String menuValue;

    private Integer menuStatus;

    private String menuUrl;

    private String menuIcon;

    private Long menuParentId;

    private Integer menuLevel;

    private Integer menuIsLeaf;

    private Integer menuDisplaySeq;

    private String menuDescription;

    private Long storeId;

    private String creator;

    private Long creatorId;

    private String lastOperator;

    private Long lastOperatorId;

    private Date createDate;

    private Date updateDate;

    
    public MenuDTO to()
    {
    	MenuDTO menuDTO=new MenuDTO();
    	menuDTO.setCreateDate(this.createDate);
    	menuDTO.setUpdateDate(this.updateDate);
    	menuDTO.setLastOperatorId(this.lastOperatorId);
    	menuDTO.setLastOperator(this.lastOperator);
    	menuDTO.setCreatorId(this.creatorId);
    	menuDTO.setCreator(creator);
    	menuDTO.setMenuId(this.menuId);
    	menuDTO.setMenuName(this.menuName);
    	menuDTO.setMenuValue(this.menuValue);
    	menuDTO.setMenuStatus(this.menuStatus);
    	menuDTO.setMenuUrl(this.menuUrl);
    	menuDTO.setMenuIcon(this.menuIcon);
    	menuDTO.setMenuParentId(this.menuParentId);
    	menuDTO.setMenuLevel(this.menuLevel);
    	menuDTO.setMenuIsLeaf(this.menuIsLeaf);
    	menuDTO.setMenuDisplaySeq(this.menuDisplaySeq);
    	menuDTO.setMenuDescription(this.menuDescription);
    	menuDTO.setStoreId(this.storeId);
    	return menuDTO;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuValue() {
        return menuValue;
    }

    public void setMenuValue(String menuValue) {
        this.menuValue = menuValue == null ? null : menuValue.trim();
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public Long getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Long menuParentId) {
        this.menuParentId = menuParentId;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Integer getMenuIsLeaf() {
        return menuIsLeaf;
    }

    public void setMenuIsLeaf(Integer menuIsLeaf) {
        this.menuIsLeaf = menuIsLeaf;
    }

    public Integer getMenuDisplaySeq() {
        return menuDisplaySeq;
    }

    public void setMenuDisplaySeq(Integer menuDisplaySeq) {
        this.menuDisplaySeq = menuDisplaySeq;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription == null ? null : menuDescription.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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