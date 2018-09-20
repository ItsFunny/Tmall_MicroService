package com.tmall.server.product.common.model;

public class TmallPropertyValue {
    private Long propertyValueId;

    private Integer propertyId;

    private String propertyValue;

    private Integer propertyDisSeq;

    public Long getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Long propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue == null ? null : propertyValue.trim();
    }

    public Integer getPropertyDisSeq() {
        return propertyDisSeq;
    }

    public void setPropertyDisSeq(Integer propertyDisSeq) {
        this.propertyDisSeq = propertyDisSeq;
    }
}