/**
*
* @author joker 
* @date 创建时间：2018年8月28日 上午8:14:24
* 
*/
package com.tmall.server.store.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joker.library.dto.ResultDTO;
import com.tmall.common.dto.CompanyDTO;
import com.tmall.common.utils.ResultUtils;
import com.tmall.server.store.common.model.TmallCompany;
import com.tmall.server.store.provider.service.ICompanyService;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月28日 上午8:14:24
 */
@RestController
@RequestMapping(value = "/auth/company")
public class CompanyAPIController
{
	@Autowired
	private ICompanyService companyService;

	/*
	 * 通过主键查询公司,需要进行权限校验
	 */
	@RequestMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResultDTO<CompanyDTO> findByCompnayId(@PathVariable("companyId") Long companyId)
	{
		try
		{
			TmallCompany company = companyService.findByCompnayId(companyId);
			if (null != company)
			{
				return ResultUtils.sucess(company.to());
			} else
			{
				return ResultUtils.fail("公司不存在");
			}
		} catch (Exception e)
		{
			return ResultUtils.fail();
		}
	}
}
