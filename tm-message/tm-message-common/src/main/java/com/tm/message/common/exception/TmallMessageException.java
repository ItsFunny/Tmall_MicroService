/**
*
* @author joker 
* @date 创建时间：2018年9月7日 上午9:00:07
* 
*/
package com.tm.message.common.exception;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
* 
* @author joker 
* @date 创建时间：2018年9月7日 上午9:00:07
*/
public class TmallMessageException extends TmallBussinessException
{

	public TmallMessageException(ErrorCodeEnum enum1)
	{
		super(enum1);
	}

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年9月7日 上午9:00:16
	*/
	private static final long serialVersionUID = 8116827034266047260L;
	
}
