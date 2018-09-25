/**
*
* @author joker 
* @date 创建时间：2018年6月13日 下午8:38:00
* 
*/
package com.joker.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 身份证号校验器
 * @author joker
 * @date 创建时间：2018年6月13日 下午8:38:00
 */
public class IDNoChecker
{
	private static final Pattern ID_NO_PARTTERN = Pattern
			.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$");
	// 省(直辖市)码表
	private static final Set<String> PROVINCE_CODES = new HashSet<>(Arrays.asList(new String[]
	{ "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44",
			"45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91" }));

	// 身份证前17位每位加权因子
	private static final int[] POWER =
	{ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	// 身份证第18位校检码
	private static final String[] REF_NUMBER =
	{ "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

	protected boolean isMatchPattern(String idNo)
	{
		return ID_NO_PARTTERN.matcher(idNo).matches();
	}

	protected boolean isValidProvinceId(String provinceId)
	{
		return PROVINCE_CODES.contains(provinceId);
	}

	/**
	 * 判断日期是否有效
	 * 
	 * @param inDate
	 * @return
	 */
	protected boolean isValidDate(String inDate)
	{
		if (inDate == null)
		{
			return false;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		if (inDate.trim().length() != dateFormat.toPattern().length())
		{
			return false;
		}
		dateFormat.setLenient(false);// 执行严格的日期匹配
		try
		{
			dateFormat.parse(inDate.trim());
		} catch (ParseException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * 计算身份证的第十八位校验码
	 * 
	 * @param cardIdArray
	 * @return
	 */
	protected String sumPower(int[] cardIdArray)
	{
		int result = 0;
		for (int i = 0; i < POWER.length; i++)
		{
			result += POWER[i] * cardIdArray[i];
		}
		return REF_NUMBER[(result % 11)];
	}

	/**
	 * 校验身份证第18位是否正确(只适合18位身份证)
	 * 
	 * @param idNo
	 * @return
	 */
	protected boolean checkIdNoLastNum(String idNo)
	{
		if (idNo.length() != 18)
		{
			return false;
		}
		char[] tmp = idNo.toCharArray();
		int[] cardidArray = new int[tmp.length - 1];
		int i = 0;
		for (i = 0; i < tmp.length - 1; i++)
		{
			cardidArray[i] = Integer.parseInt(tmp[i] + "");
		}
		String checkCode = sumPower(cardidArray);
		String lastNum = tmp[tmp.length - 1] + "";
		if (lastNum.equals("x"))
		{
			lastNum = lastNum.toUpperCase();
		}
		if (!checkCode.equals(lastNum))
		{
			return false;
		}
		return true;
	}

	public boolean validate(String idNo)
	{
		return isMatchPattern(idNo) && isValidProvinceId(idNo.substring(0, 2)) && isValidDate(idNo.substring(6, 14))
				&& checkIdNoLastNum(idNo);
	}

	public static void main(String[] args)
	{
		System.out.println(new IDNoChecker().validate("330724198205076212"));
	}

}
