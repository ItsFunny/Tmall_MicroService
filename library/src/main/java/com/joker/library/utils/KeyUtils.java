/**
*
* @author joker 
* @date 创建时间：2018年3月11日 下午5:28:19
* 
*/
package com.joker.library.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 用于生成一些独一的key
 * 
 * @author joker
 * @date 创建时间：2018年3月11日 下午5:28:19
 */
public class KeyUtils
{

	public static String generateRandomSequence()
	{
		Random random = new Random(47);
		char[] arr =
		{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'H', 'i', 'j', 'k', 'n', 'm', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'h', 'I', 'J', 'K', 'N', 'M', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '-', '_' };

		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 8; ++i)
		{
			int index = random.nextInt(arr.length);
			char c = arr[index];
			str.append(c);
		}
		int number = random.nextInt(10000);
		str.append(number);
		return new String(str);
	}

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
			return new String(buffer);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
