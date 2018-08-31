/**
*
* @author joker 
* @date 创建时间：2018年8月2日 下午9:46:32
* 
*/
package com.tmall.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
* 
* @author joker 
* @date 创建时间：2018年8月2日 下午9:46:32
*/
public  class TmallTreeComposite implements Serializable
{

	/**
	* 
	* @author joker 
	* @date 创建时间：2018年8月16日 下午4:31:15
	*/
	private static final long serialVersionUID = 1625971733126792225L;
	
//	@JsonProperty("_id")
//	private long _id=serialVersionUID;
	
//	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "_id")
//	private TmallTreeComposite parent;
	
	private List<TmallTreeComposite>childs;
	
	
	
	public TmallTreeComposite()
	{
		this.childs=new ArrayList<TmallTreeComposite>();
	}
	
	public void addChild(TmallTreeComposite composite)
	{
		this.childs.add(composite);
//		composite.setParent(this);
	}




	public List<TmallTreeComposite> getChilds()
	{
		return childs;
	}

	public void setChilds(List<TmallTreeComposite> childs)
	{
		this.childs = childs;
	}
	
	
}
