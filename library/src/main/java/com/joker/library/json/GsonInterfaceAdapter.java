/**
*
* @author joker 
* @date 创建时间：2018年5月16日 下午2:27:48
* 
*/
package com.joker.library.json;

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
 * @function: help the user when serialize and descrialize the class which has
 *            impl the interface (without this ,ide will throw exception) use:
 *            GsonBuilder builder=new GsonBuilder();
 *            builder.registerTypeAdapter(DetailInterface.class,GsonInterfaceAdapter.class);
 *            Gson gson=builder.create();
 * @author joker
 * @date 创建时间：2018年5月16日 下午2:27:48
 */
public class GsonInterfaceAdapter implements JsonSerializer<Object>, JsonDeserializer<Object>
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
	public Object deserialize(JsonElement jsonElement, Type typeOfT,
			JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
	{
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
		String className = prim.getAsString();
		Class<?> klass = getObjectClass(className);
		return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
	}

	@Override
	public JsonElement serialize(Object jsonElement, Type type, JsonSerializationContext jsonSerializationContext)
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
		jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
		return jsonObject;
	}
}