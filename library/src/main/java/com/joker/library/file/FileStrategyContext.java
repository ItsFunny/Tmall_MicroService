/**
*
* @author joker 
* @date 创建时间：2018年8月8日 下午4:50:13
* 
*/
package com.joker.library.file;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月8日 下午4:50:13
 */
public class FileStrategyContext
{
	private IFileStrategy fileStrategy;

	private boolean isFtp;

	private String ftpHost;
	private Integer ftpPort;
	private String ftpUsername;
	private String ftpPassword;

	public String upload(MultipartFile file,String storePath ,String newFileName,String key)
	{
		return this.fileStrategy.upload(file, storePath,newFileName,key);
	}
	public Boolean delete(String filePathName)
	{
		return this.fileStrategy.delete(filePathName);
	}
	public IFileStrategy getFileStrategy()
	{
		return fileStrategy;
	}

	public void setFileStrategy(IFileStrategy fileStrategy)
	{
		this.fileStrategy = fileStrategy;
	}
	

	
	public String getStoreBasePath(String key)
	{
		return this.fileStrategy.getStoreBasePath(key);
	}
	public String getVisitPrefix(String key)
	{
		return this.fileStrategy.getVisitPrefix(key);
	}
	
	
	public IFileStrategy getObject() throws Exception
	{
		if (isFtp)
		{
			if(checkEmpty())
			{
				throw new RuntimeException("如果采用ftp模式,必须设置参数");
			}else {
				this.fileStrategy=new FTPFileService(ftpHost,ftpPort,ftpUsername,ftpPassword);
			}
		}else {
			this.fileStrategy=new DefaultFileService();
		}
		return this.fileStrategy;
	}
	
	
	private boolean checkEmpty()
	{
		return (StringUtils.isEmpty(ftpHost) || StringUtils.isEmpty(ftpUsername) ||StringUtils.isEmpty(ftpPassword)
				|| ftpPort == null || ftpPort < 0);
	}


	public boolean isFtp()
	{
		return isFtp;
	}

	public void setFtp(boolean isFtp)
	{
		this.isFtp = isFtp;
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

}
