/**
*
* @author joker 
* @date 创建时间：2018年5月14日 上午10:54:40
* 
*/
package com.tmall.common.utils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tmall.common.event.GsonInterfaceAdapter;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月14日 上午10:54:40
 */
public class JsonUtils
{
	private static Gson gson = null;
	static
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Serializable.class, new GsonInterfaceAdapter<Serializable>());
		gson = gsonBuilder.create();
	}

	public static String obj2Json(Object object)
	{
		String json = gson.toJson(object);
		return json;
	}
	public static <T>List<T>json2List(String json,Type type)
	{
		return gson.fromJson(json, type);
	}

	public static <T> T json2Object(String json, Class<T> c)
	{
		T t = gson.fromJson(json, c);
		return t;
	}
}
