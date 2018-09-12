/**
*
* @author joker 
* @date 创建时间：2018年8月21日 下午10:48:41
* 
*/
package com.tmall.server.auth.common.excecption;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月21日 下午10:48:41
 */
public class AuthBizException extends TmallBussinessException
{

	public AuthBizException(ErrorCodeEnum enum1)
	{
		super(enum1);
	}
	/**
	 * 
	 * @author joker
	 * @date 创建时间：2018年8月21日 下午10:49:06
	 */
	private static final long serialVersionUID = 3357168764643539312L;

}
