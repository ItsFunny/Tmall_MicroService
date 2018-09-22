package com.tmall.server.product.common.model;

import org.springframework.beans.BeanUtils;

import com.joker.library.sqlextention.AbstractSQLExtentionModel;
import com.tmall.common.dto.PropertyDTO;

public class TmallProperty extends AbstractSQLExtentionModel {
    private Integer propertyId;

    private String propertyName;

    private Integer propertyDisSeq;

    private Boolean propertyIsSearch;
    
    public void from(PropertyDTO dto)
    {
    	//先直接这么写吧,反射还是尽量少用
    	BeanUtils.copyProperties(dto, this);
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public Integer getPropertyDisSeq() {
        return propertyDisSeq;
    }

    public void setPropertyDisSeq(Integer propertyDisSeq) {
        this.propertyDisSeq = propertyDisSeq;
    }

    public Boolean getPropertyIsSearch() {
        return propertyIsSearch;
    }

    public void setPropertyIsSearch(Boolean propertyIsSearch) {
        this.propertyIsSearch = propertyIsSearch;
    }

	@Override
	public Number getUniquekey()
	{
		return this.propertyId;
	}
}