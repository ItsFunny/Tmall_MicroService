package com.tm.message.common.model;

import java.util.Date;

import com.tmall.common.dto.MessageDTO;

public class TmallBatchMessage {
    private String messageId;

    private String messageDetail;

    private String messageFrom;

    private Integer messageStatus;

    private String messageCreator;

    private Long messageUserId;

    private Date createDate;

    private Date updateDate;
    
    public void from(MessageDTO messageDTO)
    {
    	this.messageId=messageDTO.getMessageId();
    	this.messageDetail=messageDTO.getMessageDetail();
    	this.messageFrom=messageDTO.getMessageFrom();
    	this.messageStatus=messageDTO.getMessageStatus();
    	this.messageCreator=messageDTO.getMessageCreator();
    	this.messageUserId=messageDTO.getMessageUserId();
    	this.createDate=messageDTO.getCreateDate();
    	this.updateDate=messageDTO.getUpdateDate();
    }
    

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail == null ? null : messageDetail.trim();
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom == null ? null : messageFrom.trim();
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageCreator() {
        return messageCreator;
    }

    public void setMessageCreator(String messageCreator) {
        this.messageCreator = messageCreator == null ? null : messageCreator.trim();
    }

    public Long getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(Long messageUserId) {
        this.messageUserId = messageUserId;
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