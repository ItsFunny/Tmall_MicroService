/**
*
* @author joker 
* @date 创建时间：2018年8月9日 上午9:12:41
* 
*/
package com.joker.library.file;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
* 
* @author joker 
* @date 创建时间：2018年8月9日 上午9:12:41
*/
public abstract class AbstractFIleStrategy implements IFileStrategy
{

	//访问方式的前缀,如默认/imgs 等 ftp形式则是ip地址或者域名
	/*
	 * 访问资源的前缀
	 * 默认的单系统形式:/imgs  imgs在webapp下
	 * 而ftp: /asdd   通常是nginx 做了反向代理之后的地址
	 */
	
	private  Map<String, String>basePathMap;
	
	private Map<String, String>visitPrefixMap;
	
	
	public AbstractFIleStrategy()
	{
		this.basePathMap=new HashMap<String, String>();
		this.visitPrefixMap=new HashMap<String, String>();
	}
	
	


	public Map<String, String> getBasePathMap()
	{
		return basePathMap;
	}

	public void setBasePathMap(Map<String, String> basePathMap)
	{
		this.basePathMap = basePathMap;
	}

	@Override
	public String getStoreBasePath(String key)
	{
		 String res = this.basePathMap.get(key);
		return res;
	}



	@Override
	public String getVisitPrefix(String key)
	{
		String prefix = this.visitPrefixMap.get(key);
		if(StringUtils.isAllBlank(prefix))
		{
			 throw new RuntimeException(key+"对应的访问前缀地址不存在");
		}
		return prefix;
	}



	public Map<String, String> getVisitPrefixMap()
	{
		return visitPrefixMap;
	}



	public void setVisitPrefixMap(Map<String, String> visitPrefixMap)
	{
		this.visitPrefixMap = visitPrefixMap;
	}



	
	
}
