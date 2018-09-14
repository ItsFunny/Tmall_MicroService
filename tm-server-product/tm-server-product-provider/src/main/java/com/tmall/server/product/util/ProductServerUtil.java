/**
*
* @author joker 
* @date 创建时间：2018年9月14日 下午5:34:42
* 
*/
package com.tmall.server.product.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tmall.common.enums.ErrorCodeEnum;
import com.tmall.server.product.common.model.TmallCategory;
import com.tmall.server.product.exception.TmallProductException;

/**
* 
* @author joker 
* @date 创建时间：2018年9月14日 下午5:34:42
*/
public class ProductServerUtil
{
	public static void sortByCategoryId(List<TmallCategory>categories)
	{
		if(null==categories ||categories.isEmpty())
		{
			throw new TmallProductException(ErrorCodeEnum.NULL_POINTER);
		}
		Collections.sort(categories,new Comparator<TmallCategory>()
		{
			@Override
			public int compare(TmallCategory o1, TmallCategory o2)
			{
				return o1.getCategoryId()<=o2.getCategoryId()?-1:1;
			}
		});
	}
	public static void main(String[] args)
	{
		List<TmallCategory>categories=new ArrayList<TmallCategory>();
		TmallCategory tmallCategory=new TmallCategory();
		tmallCategory.setCategoryId(1);
		categories.add(tmallCategory);
		TmallCategory tmallCategory2=new TmallCategory();
		tmallCategory2.setCategoryId(2);
		categories.add(tmallCategory2);
		 sortByCategoryId(categories);
		for (TmallCategory tmallCategory3 : categories)
		{
			System.out.println(tmallCategory3.getCategoryId());
		}
	}
}
