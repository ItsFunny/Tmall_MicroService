/**
*
* @author joker 
* @date 创建时间：2018年9月4日 上午9:02:39
* 
*/
package com.tmall.batch.consumer;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.joker.library.mq.AppEvent;
import com.joker.library.mq.AppEventConsumer;
import com.joker.library.utils.JsonUtil;
import com.rabbitmq.client.Channel;
import com.tmall.batch.exception.TmallBatchException;
import com.tmall.batch.mq.AbstractMQChannelConsumer;
import com.tmall.common.dto.RecordDTO;
import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.common.mq.TmallMQEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author joker
 * @date 创建时间：2018年9月4日 上午9:02:39
 */
@Slf4j
public class UserRecordListener extends AbstractMQChannelConsumer
{
	@Autowired
	private QueryRunner queryRunner;

	@Override
	public void doConsume(AppEvent event, Long deliveryTag, Channel channel)
	{
		log.info("[UserRecordListener]接收到消息:{}", event);
		long startTime = System.currentTimeMillis();
		long validCount = insertToDb(event);
		if (validCount > 0)
		{
			// 第二个参数表示是否1次ack多条消息
			try
			{
				channel.basicAck(deliveryTag, false);
			} catch (IOException e)
			{
				log.error("[UserRecordListener]确认消费信息失败,失败原因:{}", e.getMessage());
				throw new TmallBatchException(ErrorCodeEnum.INTERNAL_SERVICE_ERROR, e);
			}
			// 插入成功
			log.info("[UserRecordListener]结束了消息,总共耗时:{}秒", (System.currentTimeMillis() - startTime) / 1000.0);
		} else
		{
			// 插入失败
			// 代表消费者拒绝一条或者多条消息，第二个参数表示一次是否拒绝多条消息，第三个参数表示是否把当前消息重新入队
			log.error("[UserRecordListener]无法将操作记录插入到数据库中,重新投放到broker中,event:{}", event);
			throw new TmallBatchException(ErrorCodeEnum.SYS_BATCH_CONSUME_MESSAGE_ERROR);
		}
	}

	@Override
	public String getType()
	{
		return TmallMQEnum.USER_RECORD.getRoutinKey();
	}

	@Transactional
	public long insertToDb(AppEvent event)
	{

		Serializable dataSerializable = event.getData();
		if (dataSerializable instanceof RecordDTO)
		{
			long res = doInsert((RecordDTO) dataSerializable);
			return res;
		} else
		{
			log.error("[UserRecordListener] 收到的数据类型不是需要的类型,类型为:{},需要的类型为:{}", dataSerializable.getClass(),
					RecordDTO.class);
			return -1;
		}
	}

	public long doInsert(RecordDTO data)
	{
		String sql = "insert into tmall_record (user_id,realname,record_detail,create_date) values (?,?,?,?)";
		List<Object> l = new LinkedList<Object>();
		l.add(data.getUserId());
		l.add(data.getRealname());
		l.add(data.getRecordDetail());
		l.add(data.getCreateDate());
		try
		{
			Object count = queryRunner.insert(sql, new ScalarHandler<Object>(), l.toArray());
			if (count == null)
			{
				return 0;
			} else
			{
				return (long) count;
			}
		} catch (SQLException e)
		{
			log.error("[UserRecordListener]插入数据的时候出错:{}", data, e);
			return 0;
		}
	}

}
