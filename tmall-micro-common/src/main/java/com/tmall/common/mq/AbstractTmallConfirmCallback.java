/**
*
* @author joker 
* @date 创建时间：2018年9月5日 下午7:56:00
* 
*/
package com.tmall.common.mq;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.exception.TmallBussinessException;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月5日 下午7:56:00
 */
@Data
public abstract class AbstractTmallConfirmCallback implements ConfirmCallback
{
	
	public static final String USER_RECORD_TYPE="user_record";
	
	private String type;

	private AbstractTmallConfirmCallback nextCallback;

	public AbstractTmallConfirmCallback(String type)
	{
		this.type = type;
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause)
	{
		if (!(correlationData instanceof TmallCorrelationDataWrapper))
		{
			throw new TmallBussinessException(ErrorCodeEnum.MQ_ILLEGAL_CORRELATIONDATA);
		}
		TmallCorrelationDataWrapper wrapper = (TmallCorrelationDataWrapper) correlationData;
		if (wrapper.getType().equals(this.type))
		{
			this.doHandlerConfirm(wrapper, ack, cause);
		} else if (this.nextCallback != null)
		{
			this.nextCallback.doHandlerConfirm(wrapper, ack, cause);
		}
	}

	private void doHandlerConfirm(TmallCorrelationDataWrapper wrapper, boolean ack, String cause)
	{
		if (ack)
		{
			doSuccessConfirm(wrapper);
		} else
		{
			doFailConfirm(wrapper, cause);
		}
	}

	protected abstract void doSuccessConfirm(TmallCorrelationDataWrapper wrapper);

	protected abstract void doFailConfirm(TmallCorrelationDataWrapper wrapper, String cause);

}
