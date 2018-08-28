package com.tmall.server.user.common.model;

import java.util.Date;

import com.tmall.common.dto.UserDTO;


public class TmallUser {
    private Long userId;

    private String username;

    private String password;

    private String realname;

    private String mobile;

    private String email;

    private String openid;

    private String idCard;

    private Integer status;

    private Date createDate;

    private Date updateDate;

    private Date lastLoginDate;

    private String lastLoginIp;
    //2018-07-29 22:02 add
    private Integer tableNum;
    
    public void to(UserDTO userDTO)
    {
    	userDTO.setCreateDate(this.createDate);
    	userDTO.setEmail(this.email);
    	userDTO.setIDCard(this.idCard);
    	userDTO.setLastLoginDate(this.lastLoginDate);
    	userDTO.setLastLoginIp(this.lastLoginIp);
    	userDTO.setMobile(this.mobile);
    	userDTO.setOpenid(this.openid);
    	userDTO.setPassword(this.password);
    	userDTO.setStatus(this.status);
    	userDTO.setUpdateDate(this.updateDate);
    	userDTO.setUserId(this.userId);
    	userDTO.setUsername(this.username);
    	userDTO.setTableNum(this.tableNum);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

	public Integer getTableNum()
	{
		return tableNum;
	}

	public void setTableNum(Integer tableNum)
	{
		this.tableNum = tableNum;
	}
}