package com.tmall.server.auth.common.model;

import com.tmall.common.dto.TmallConfigTemplateDTO;

public class TmallTemplate
{
	private Integer templateId;

	private String templateName;

	private Integer templateType;

	private String templateDetial;

	public TmallConfigTemplateDTO to()
	{
		TmallConfigTemplateDTO dto = new TmallConfigTemplateDTO();
		dto.setTemplateId(this.templateId);
		dto.setTemplateDetial(this.templateDetial);
		dto.setTemplateType(this.templateType);
		dto.setTemplateName(this.templateName);
		return dto;
	}

	public Integer getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(Integer templateId)
	{
		this.templateId = templateId;
	}

	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		this.templateName = templateName == null ? null : templateName.trim();
	}

	public Integer getTemplateType()
	{
		return templateType;
	}

	public void setTemplateType(Integer templateType)
	{
		this.templateType = templateType;
	}

	public String getTemplateDetial()
	{
		return templateDetial;
	}

	public void setTemplateDetial(String templateDetial)
	{
		this.templateDetial = templateDetial == null ? null : templateDetial.trim();
	}
}