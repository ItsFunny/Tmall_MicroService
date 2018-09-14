/**
*
* @author joker 
* @date 创建时间：2018年5月25日 上午10:44:47
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月25日 上午10:44:47
 */
@Data
public class CategoryDTO implements Serializable
{
	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年5月28日 下午1:27:47
	 */
	private static final long serialVersionUID = -274465288896692706L;
	private Integer categoryId;

	private String categoryName;

	private Integer categoryDisplaySeq;

	private Integer categoryPid;

	private Integer status;

	private String creator;

	private Long creatorUserId;

	private String lastOperator;

	private Long lastOperatorId;

	private Date createDate;

	private Date updateDate;

	private Integer createTime;

	@Override
	public int hashCode()
	{
		int i = 27;
		int result = 17;
		result *= i + this.getCategoryId() + result;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		CategoryDTO categoryDTO = (CategoryDTO) obj;
		return this.getCategoryId().equals(categoryDTO.getCategoryId());
	}

	public Integer getCategoryPid()
	{
		return categoryPid;
	}

	public void setCategoryPid(Integer categoryPid)
	{
		this.categoryPid = categoryPid;
	}


}
