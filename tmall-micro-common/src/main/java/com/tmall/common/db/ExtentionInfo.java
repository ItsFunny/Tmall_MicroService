/**
*
* @author joker 
* @date 创建时间：2018年6月4日 上午9:26:28
* 
*/
package com.tmall.common.db;

import com.tmall.common.other.ISQLExtentionCRUDDao;

import lombok.Data;

/**
 * 
 * @author joker
 * @date 创建时间：2018年6月4日 上午9:26:28
 */
@Data
public class ExtentionInfo
{
	private Long id;

	private Integer sqlTableTotalCount;

	// 总共有几个库,采取下标的方式注册,如0号db则在dbs[0]中
	private DBInfo[] dbs;
	private Integer dbCounts;

	// 表对应的db信息
	@Data
	public static class DBInfo
	{
		// 这个表中有几个table
		private Integer tableCounts;
		// 每个表的信息,可自主选择是否添加
//		private TableInfo[] tables;
		// 这个db对应的service,或者dao更加合适
		private ISQLExtentionCRUDDao baseDao;
	}

	@Data
	public static class TableInfo
	{
		// 表的名称,如user_0
		private String tableName;
	}

	/**
	 * 作为一个基础类,其他service继承这个接口之后
	 * 
	 * @author joker
	 * @date 创建时间：2018年9月15日 下午12:04:19
	 */
	public static interface SQLExtentionBaseService<T>
	{

		/**
		 * @param index
		 *            插入哪个表,比如插入第二个表:则表明最终会变成:user_2
		 * @param t
		 *            插入的数据
		 * @return
		 * @author joker
		 * @date 创建时间：2018年9月15日 下午1:28:57
		 */
		Integer add(Integer index, T t);
	}

}
