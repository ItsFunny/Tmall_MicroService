/**
*
* @author joker 
* @date 创建时间：2018年8月8日 下午5:54:36
* 
*/
package com.joker.library.file;

import org.apache.commons.net.ftp.FTPClient;

/**
 * 
 * @author joker
 * @date 创建时间：2018年8月8日 下午5:54:36
 */
public interface BlockingPool<T>
{
	T get();

	void close(FTPClient client);
	
	
	long getSize();

	long getMaxSize();

	void setMaxSize(long newSize);
}
