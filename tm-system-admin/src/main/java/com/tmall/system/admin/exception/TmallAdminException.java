/**
*
* @author joker 
* @date 创建时间：2018年9月1日 上午10:44:06
* 
*/
package com.tmall.system.admin.exception;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
* 
* @author joker 
* @date 创建时间：2018年9月1日 上午10:44:06
*/
public class TmallAdminException extends TmallBussinessException
{

	public TmallAdminException(ErrorCodeEnum enum1)
	{
		super(enum1);
		// TODO Auto-generated constructor stub
	}

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月1日 上午10:44:30
	*/
	private static final long serialVersionUID = -7883363626532201L;

}
