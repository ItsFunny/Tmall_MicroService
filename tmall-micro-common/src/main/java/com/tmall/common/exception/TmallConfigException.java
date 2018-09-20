/**
*
* @Description
* @author joker 
* @date 创建时间：2018年9月20日 下午6:45:59
* 
*/
package com.tmall.common.exception;

import com.tmall.common.enums.ErrorCodeEnum;

/**
 * 
 * @When
 * @Description
 * @Detail
 * @author joker
 * @date 创建时间：2018年9月20日 下午6:45:59
 */
public class TmallConfigException extends TmallBussinessException
{
	public TmallConfigException(ErrorCodeEnum enum1)
	{
		super(enum1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @Description
	 * @author joker
	 * @date 创建时间：2018年9月20日 下午6:46:12
	 */
	private static final long serialVersionUID = 4066509943371838440L;

	public TmallConfigException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public TmallConfigException(ErrorCodeEnum enum1, String msg)
	{
		super(enum1, msg);
	}

	public TmallConfigException(ErrorCodeEnum enum1, Throwable cause)
	{
		super(enum1, cause);
	}

}
