/**
*
* @author joker 
* @date 创建时间：2018年5月16日 下午2:27:48
* 
*/
package com.rebuildtmall.tmall_micro_common.event;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @author joker
 * @date 创建时间：2018年5月16日 下午2:27:48
 */
public class GsonInterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T>
{

	private static final String CLASSNAME = "CLASSNAME";
	private static final String DATA = "DATA";


	/******
	 * Helper method to get the className of the object to be deserialized
	 *****/
	public Class<?> getObjectClass(String className)
	{
		try
		{
			return Class.forName(className);
		} catch (ClassNotFoundException e)
		{
			throw new JsonParseException(e.getMessage());
		}
	}

	@Override
	public T deserialize(JsonElement jsonElement, Type typeOfT,
			JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
	{
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
		String className = prim.getAsString();
		System.out.println(className);
		Class<?> klass = getObjectClass(className);
		return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
	}
	@Override
	public JsonElement serialize(T jsonElement, Type type, JsonSerializationContext jsonSerializationContext)
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
		jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
		return jsonObject;
	}
}