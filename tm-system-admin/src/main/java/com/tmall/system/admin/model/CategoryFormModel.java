/**
*
* @author joker 
* @date 创建时间：2018年9月16日 下午5:12:01
* 
*/
package com.tmall.system.admin.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.joker.library.utils.CommonUtils;
import com.joker.library.utils.DateUtils;
import com.sun.istack.internal.NotNull;
import com.tmall.common.dto.UserDTO;
import com.tmall.common.enums.CommonStatusEnum;
import com.tmall.server.product.common.model.TmallCategory;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月16日 下午5:12:01
 */
@Data
public class CategoryFormModel
{
	private Integer categoryId;

	@NotNull
	private String categoryName;

	@Max(value=100)
	@Min(value=0)
	private Integer categoryDisplaySeq=0;

	
	private Integer categoryPid=0;

	private Integer status;

	private String creator;

	private Long creatorUserId;

	private String lastOperator;

	private Long lastOperatorId;

	private Date createDate;

	private Date updateDate;

	private Integer createTime;
	
	public CategoryFormModel()
	{
		this.createDate=new Date();
		this.createTime=((Number)DateUtils.getCurrentDay()).intValue();
		this.status=CommonStatusEnum.enable.ordinal();
	}

}
