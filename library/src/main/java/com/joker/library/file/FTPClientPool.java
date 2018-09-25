/**
*
* @author joker 
* @date 创建时间：2018年8月8日 下午5:17:19
* 
*/
package com.joker.library.file;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月8日 下午5:17:19
 */
public class FTPClientPool implements BlockingPool<FTPClient>
{

	private Logger log = LoggerFactory.getLogger(FTPClientPool.class);
	private final FTPBean configBean;

	private volatile AtomicInteger onlineClientCount;

	private Long defalutWaitTimeMills = 1000l;

	private Integer defaultClinetQueueSize = 10;

	private long maxOnLineClientSize;

	private LinkedBlockingQueue<Object> ftpRequestQueue;

	private LinkedBlockingQueue<FTPClient> reuseClients;

	public FTPClientPool(FTPBean configBean)
	{
		super();
		this.configBean = configBean;
		this.onlineClientCount = new AtomicInteger(0);
		this.maxOnLineClientSize = defaultClinetQueueSize;
		this.ftpRequestQueue = new LinkedBlockingQueue<>();
		this.reuseClients = new LinkedBlockingQueue<>(defaultClinetQueueSize);
	}

	public String getHost()
	{
		return this.configBean.getFtpHost();
	}

	private boolean isReuseClientsQueueAbleAdd()
	{

		// if(onlineClientCount.get()>=maxOnLineClientSize)
		// {
		// return false;
		// }else {
		// return true;
		// }

		int size = reuseClients.size();

		if (size < defaultClinetQueueSize / 2)
		{
			return true;
		} else if (size > defaultClinetQueueSize && this.maxOnLineClientSize > this.defaultClinetQueueSize)
		{
			LinkedBlockingQueue<FTPClient> t = new LinkedBlockingQueue<FTPClient>((int) maxOnLineClientSize);
			t.addAll(this.reuseClients);
			this.reuseClients = t;
			return true;
		} else if (size > defaultClinetQueueSize && size < maxOnLineClientSize)
		{
			return true;
		} else
		{
			return false;
		}
	}

	private boolean isAbleCreateOwnClient()
	{
		int size = onlineClientCount.get();
		if (size < defaultClinetQueueSize)
		{
			return true;
		} else if (size >= defaultClinetQueueSize && size < maxOnLineClientSize)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public void close(FTPClient client)
	{
		if (null == client)
		{
			return;
		}

		if (!ftpRequestQueue.isEmpty() && isReuseClientsQueueAbleAdd())
		{
			this.reuseClients.add(client);
		} else
		{
			try
			{
				client.disconnect();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	protected void checkConfig()
	{
		 this.configBean.checkIllegal();
	}

	protected boolean isNeedRecycle()
	{
		return true;
	}

	@Override
	public long getSize()
	{
		return 0;
	}

	@Override
	public synchronized long getMaxSize()
	{
		return this.onlineClientCount.get();
	}

	@Override
	public void setMaxSize(long newSize)
	{
		this.maxOnLineClientSize = newSize;
	}

	public FTPBean getConfigBean()
	{
		return configBean;
	}

	public static class FTPBean
	{

		private String ftpHost;
		private Integer ftpPort;
		private String ftpUsername;
		private String ftpPassword;

		private Integer buffSize;

		public FTPBean(String ftpHost, Integer ftpPort, String ftpUsername, String ftpPassword, Integer buffSize)
		{
			super();
			this.ftpHost = ftpHost;
			this.ftpPort = ftpPort;
			this.ftpUsername = ftpUsername;
			this.ftpPassword = ftpPassword;
			this.buffSize = buffSize;
		}

		public FTPBean(String ftpHost, Integer ftpPort, String ftpUsername, String ftpPassword)
		{
			this(ftpHost, ftpPort, ftpUsername, ftpPassword, 1024000);
		}

		public String getFtpHost()
		{
			return ftpHost;
		}

		public void setFtpHost(String ftpHost)
		{
			this.ftpHost = ftpHost;
		}

		public Integer getFtpPort()
		{
			return ftpPort;
		}

		public void setFtpPort(Integer ftpPort)
		{
			this.ftpPort = ftpPort;
		}

		public String getFtpUsername()
		{
			return ftpUsername;
		}

		public void setFtpUsername(String ftpUsername)
		{
			this.ftpUsername = ftpUsername;
		}

		public String getFtpPassword()
		{
			return ftpPassword;
		}

		public void setFtpPassword(String ftpPassword)
		{
			this.ftpPassword = ftpPassword;
		}

		public void checkIllegal()
		{
			if (StringUtils.isEmpty(this.ftpHost))
			{
				throw new RuntimeException("ftpHost cant be null");
			} else if (StringUtils.isEmpty(this.ftpUsername))
			{
				throw new RuntimeException("ftpUsername cant be null");
			} else if (StringUtils.isEmpty(this.ftpPassword))
			{
				throw new RuntimeException(" ftp password cant be null");
			} else if (null == this.ftpPort || this.ftpPort < 0)
			{
				this.ftpPort = 21;
			}
		}

		public Integer getBuffSize()
		{
			return buffSize;
		}

		public void setBuffSize(Integer buffSize)
		{
			this.buffSize = buffSize;
		}
	}

	private FTPClient createClient()
	{
		// FTP连接对象
		FTPClient ftpClient = null;
		try
		{
			ftpClient = new FTPClient();
			// 设置FTP服务器IP和端口
			ftpClient.connect(this.configBean.getFtpHost(), this.configBean.getFtpPort());
			// 设置超时时间,毫秒
			ftpClient.setConnectTimeout(50000);
			// 登录FTP
			ftpClient.login(this.configBean.getFtpUsername(), this.configBean.getFtpPassword());

			// 设置被动传输模式
			ftpClient.enterLocalPassiveMode();
			// ftpClient.enterRemotePassiveMode();
			// 二进制传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 设置读入内存文件大小
			ftpClient.setBufferSize(this.configBean.getBuffSize());

			// 获取FTP连接状态码 ，大于等于200 小于300状态码表示连接正常
			int connectState = ftpClient.getReplyCode();

			// 连接失败重试
			int reNum = 0;
			while (!FTPReply.isPositiveCompletion(connectState) && reNum < 3)
			{
				ftpClient.disconnect();
				++reNum;
				ftpClient.login(this.configBean.getFtpUsername(), this.configBean.getFtpPassword());
			}
			if (reNum >= 3)
			{
				throw new RuntimeException("无法连接远程ftp服务器");
			}
		} catch (Exception e)
		{
			throw new RuntimeException("无法连接远程ftp服务器", e);
		}
		return ftpClient;
	}

	@Override
	public FTPClient get()
	{
		/*
		 * 1.如果重利用队列中有空闲的client ,则直接取出然后返回 2.没有空闲的client
		 * 则尝试新建一个clien,同时如果正在工作的client超出上限的则将请求放入等待队列中
		 * 3.如果等待时间完了也无法得到client,则返回结果同时去除此次请求
		 */
		if (!reuseClients.isEmpty())
		{
			// lock.tryLock(timeout, unit)
			try
			{
				return reuseClients.take();
			} catch (InterruptedException e)
			{
				log.error("[GetClientFromClientPoll] try get client from reuseQueue  occur error :{}", e.getMessage(),
						e);
				return null;
			}
		}
		if (isAbleCreateOwnClient())
		{
			return createClient();
		}
		// 在线容量已经满负荷->则加入请求队列中,那样其他的client close的时候就不会释放了
		// synchronized (this.ftpRequestQueue)
		// {
		// 这个需要额外处理,用uuid行吗
		String key = UUID.randomUUID().toString();
		this.ftpRequestQueue.add(key);
		try
		{
			FTPClient client = this.reuseClients.poll(defalutWaitTimeMills, TimeUnit.MILLISECONDS);
			return client;
		} catch (InterruptedException e)
		{
			log.error("[GetClientFromClientPoll] try to get client occured error from reused queue {}", e.getMessage(),
					e);
			return null;
		} finally
		{
			boolean remove = this.ftpRequestQueue.remove(key);
			if (!remove)
			{
				log.error("[DeleteRequestFromReuqestQueue] fail ,maybe the uuid is duplicated");
			}
		}
		// }

	}

	public Integer getDefaultClinetQueueSize()
	{
		return defaultClinetQueueSize;
	}

	public void setDefaultClinetQueueSize(Integer defaultClinetQueueSize)
	{
		this.defaultClinetQueueSize = defaultClinetQueueSize;
	}

}
