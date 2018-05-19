/**
*
* @author joker 
* @date 创建时间：2018年5月15日 下午8:43:11
* 
*/
package com.rebuildtmall.tmall_batch.amqp.consumer;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rebuildtmall.tmall_batch.amqp.AmqpListener;
import com.rebuildtmall.tmall_batch.model.UserOffsiteRecord;
import com.rebuildtmall.tmall_batch.service.UserSafeService;
import com.tmall.common.enums.RabbitMQEnum;
import com.tmall.common.event.AppEvent;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月15日 下午8:43:11
 */
public class UserOffsiteAmqpListener implements AmqpListener
{
	private Logger logger = LoggerFactory.getLogger(UserOffsiteAmqpListener.class);

	@Autowired
	private UserSafeService userSafeService;

	@Override
	public String queueName()
	{
		return RabbitMQEnum.USER_ABNORMAL_OFFSITE.getQueueName();
	}

	@Override
	public void process(AppEvent event)
	{
		HashMap<String, Object> map = event.getData();
		String userId = map.get("userId").toString();
		logger.info("begin operate user {} offsite message", userId);
		String ip = map.get("ip").toString();
		Long startTime = event.getBeginTimeMills();
		UserOffsiteRecord record = new UserOffsiteRecord();
		record.setUserId(Long.parseLong(userId));
		record.setIp(ip);
		record.setId(UUID.randomUUID().toString());
		userSafeService.recordUserOffsiteRecord(record);
		logger.info("end operate user {} offsite message,cost time {} seconds", userId,
				System.currentTimeMillis() - startTime);
	}

	@Override
	public String routingKeyValue()
	{
		return RabbitMQEnum.USER_ABNORMAL_OFFSITE.getRoutingKey();
	}

}
