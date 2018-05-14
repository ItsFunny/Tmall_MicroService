/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午11:11:51
* 
*/
package org.login_server.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 上午11:11:51
 */
public class KeyUtils
{

	/**
	 * MD5加密
	 * 
	 * @param password
	 * @return
	 * @author joker
	 * @date 创建时间：2018年3月5日 下午7:26:17
	 */
	public static String md5Encrypt(String password)
	{

		try
		{
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result)
			{
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1)
				{
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return "";
		}
	}

}
