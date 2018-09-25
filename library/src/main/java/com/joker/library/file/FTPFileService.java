package com.joker.library.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.joker.library.exception.FTPBusynessException;
import com.joker.library.file.FTPClientPool.FTPBean;

public class FTPFileService extends AbstractFIleStrategy
{

	private Logger log = LoggerFactory.getLogger(FTPFileService.class);

	private FTPClientPool singletonFTPPool;
	
	private String httpPortal="http://";
	
	private String encode = Charset.defaultCharset().toString();

	private String SERVER_CHARSET = "ISO-8859-1";

	// FTP下载时读入内存的大小

	public FTPFileService(String host, Integer port, String username, String password)
	{
		FTPBean bean = new FTPBean(host, port, username, password);
		this.singletonFTPPool = new FTPClientPool(bean);
	}


	@Override
	public String upload(MultipartFile file, String ftpPath, String newFileName,String key)
	{
		FTPClient client = singletonFTPPool.get();
		if (null == client)
		{
			throw new FTPBusynessException("ftp使用繁忙,没有可用资源");
		}
		boolean flag = false;
		InputStream in = null;
		try
		{
			String filePath = file.getOriginalFilename();
			// 获取文件后缀
			String suffix = getSuffix(filePath);
			// 路径转码，处理中文
			ftpPath = changeEncode(client, ftpPath);
			newFileName = changeEncode(client, newFileName + suffix);
			// 判断目标文件夹是否存在,不存在就创建
			if (!client.changeWorkingDirectory(ftpPath))
			{
				client.makeDirectory(ftpPath);
				client.changeWorkingDirectory(ftpPath);
			}
			// 上传文件
			// File file = new File(filePath);
			// in = new FileInputStream(file);
			in = file.getInputStream();
			flag = client.storeFile(newFileName, in);
			if (flag)
			{
				log.info("文件上传成功：" + filePath);
				return this.httpPortal+this.singletonFTPPool.getHost() + File.separator
						+ ftpPath.substring(ftpPath.indexOf(getVisitPrefix(key))) + newFileName ;
			}
		} catch (Exception e)
		{
			log.error(e.getMessage(), e);
		} finally
		{
			// 这里设置 性能
			try
			{
				if (in != null)
				{
					in.close();
				}
				singletonFTPPool.close(client);
			} catch (IOException e)
			{
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	/**
	 * 根据FTP编码集转换文件路径，防止中文乱码，并设置FTP连接的编码集
	 * 
	 * @param ftpClient
	 *            FTP连接对象
	 * @param path
	 *            文件路径
	 * @return 转码后的文件路径
	 */
	public String changeEncode(FTPClient ftpClient, String path) throws IOException
	{
		synchronized (encode)
		{
			int status = ftpClient.sendCommand("OPTS UTF8", "ON");
			// 判断FTP服务器是否支持UTF -8,支持使用UTF-8 否则使用本地编码集
			if (FTPReply.isPositiveCompletion(status))
			{
				encode = "UTF-8";
			}

			log.info("FTP使用编码集：" + encode);

			ftpClient.setControlEncoding(encode);
			path = new String(path.getBytes(encode), SERVER_CHARSET);
		}

		return path;
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 *            文件名或文件全路径
	 * @return 文件后缀
	 */
	public String getSuffix(String fileName)
	{
		String suffix = "";

		int index = fileName.lastIndexOf(".");
		if (index != -1)
		{
			suffix = fileName.substring(index);
			log.info("获取文件后缀名成功，文件名：" + fileName + " 后缀名：" + suffix);
		} else
		{
			log.warn("获取文件后缀名失败,文件名" + fileName);
		}
		return suffix;
	}

	public String getHttpPortal()
	{
		return httpPortal;
	}

	public void setHttpPortal(String httpPortal)
	{
		this.httpPortal = httpPortal;
	}


	@Override
	public Boolean delete(String filePathName)
	{
		FTPClient client = singletonFTPPool.get();
		try
		{
			return  delDirOrFile(client,filePathName);
		} finally
		{
			singletonFTPPool.close(client);
		}
	}
	
	private boolean delDirOrFile(FTPClient client,String filePathName)
	{
		Boolean res=false;
		if (null == client)
		{
			throw new FTPBusynessException("ftp使用繁忙,没有可用资源");
		}
		try
		{
			
			boolean isExists = client.changeWorkingDirectory(filePathName);
			if(!isExists)
			{
				return true;
			}
			FTPFile[] ftpFiles = client.listFiles(filePathName);
			if(null==ftpFiles || ftpFiles.length==0)
			{
				res = client.deleteFile(changeEncode(client, filePathName));
			}else {
				for (FTPFile ftpFile : ftpFiles)
				{
					String name = ftpFile.getName();
					if(name.equals(".")||name.equals(".."))
					{
						continue;
					}
					String childPathName=filePathName+File.separator+name;
					if(ftpFile.isDirectory())
					{
						delDirOrFile(client, childPathName);
					}else {
						client.deleteFile(childPathName);
					}
				}
				//不用为每个文件进行统计是否删除了,如果文件夹下还有文件存在,是无法删除的,下面的res直接为false
				client.changeWorkingDirectory(filePathName);
				res=client.removeDirectory(filePathName);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return res;
	}

}
