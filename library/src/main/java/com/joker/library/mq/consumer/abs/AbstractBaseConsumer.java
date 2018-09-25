/**
*
* @author joker 
* @date 创建时间：2018年9月8日 下午4:29:19
* 
*/
package com.joker.library.mq.consumer.abs;


/**
 * 
 * @author joker
 * @date 创建时间：2018年9月8日 下午4:29:19
 */
public abstract class AbstractBaseConsumer
{
	@SuppressWarnings("unused")
	private String type;

	public abstract String getType();

	public void setType(String type)
	{
		this.type = type;
	}

}
