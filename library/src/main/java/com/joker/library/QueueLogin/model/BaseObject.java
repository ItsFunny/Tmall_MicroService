/**
*
* @author joker 
* @date 创建时间：2018年2月2日 下午3:56:15
* 
*/
package com.joker.library.QueueLogin.model;

/**
* 
* @author joker 
* @date 创建时间：2018年2月2日 下午3:56:15
*/
public class BaseObject
{
	private static Integer iId=0;
	private String id=String.valueOf(iId++);

	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public int hashCode()
	{
		int result=37;
		result=19*result+id.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		BaseObject object=(BaseObject) obj;
		return object.getId().equals(this.id);
	}

	@Override
	public String toString()
	{
		return "BaseObject [id=" + id + "]";
	}
	
}
