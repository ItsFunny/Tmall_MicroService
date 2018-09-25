/**
*
* @author joker 
* @date 创建时间：2018年8月8日 下午4:45:59
* 
*/
package com.joker.library.file;


import org.springframework.web.multipart.MultipartFile;

/**
* 
* @author joker 
* @date 创建时间：2018年8月8日 下午4:45:59
*/
public interface IFileStrategy
{
	String IMG_TYPE="img";
	
	
	
	String upload(MultipartFile file,String storePath ,String newFileName,String key);
	
	Boolean delete(String filePathName);
	
	
	
	String getStoreBasePath(String key);
	
	
	
	String getVisitPrefix(String key);
}
