/**
*
* @author joker 
* @date 创建时间：2018年8月30日 下午8:19:04
* 
*/
package com.tmall.tm_test;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

/**
* 
* @author joker 
* @date 创建时间：2018年8月30日 下午8:19:04
*/
class Node<T>
{
	public T data;
	
	public Node<T> next;
	
	public Node<T> prev;
	public Node(T t)
	{
		this.data=t;
	}
}
class MyDoubleLinkedList<T>
{
	public Node<T> head;
	

	
	public int length;
	
	public void addNode(int index,Node<T> data)
	{
		if(null==data)
		{
			System.out.println("插入的元素不可为空");
			return;
		}
		if(length==0)
		{
			System.out.println("列表为空");
			return;
		}else if(index>length)
		{
			System.out.println("插入下标超出界限了");
			return;
		}
		Node<T>ptemp=head;
		int i=1;
		while(i<index-1)
		{
			ptemp=ptemp.next;
			i++;
		}
		data.next=ptemp.next;
		ptemp.next.prev=data;
		ptemp.next=data;
		length++;
	}
	public void filterData(Node<T> data)
	{
		if(null==data)
		{
//			throw new NullPointerException("插入的元素不可为空");
			System.out.println("插入的元素不可为空");
			return;
		}
	}
	public void addNode(Node<T> data)
	{
		filterData(data);
		if(null!=head)
		{
			Node<T> temp=head;
			while(temp.next!=null)
			{
				temp=temp.next;
			}
			temp.next=data;
			data.prev=temp;
			data.next=null;
		}else {
			head=data;
		}
		length++;
	}
	public <T> void show(MyDoubleLinkedList<T>list)
	{
		if(null==list)
		{
			throw new NullPointerException("链表不能为空");
		}
		Node<T> ptemp=list.head;
		StringBuilder sb=new StringBuilder();
		while(ptemp!=null)
		{
			sb.append("_"+ptemp.data);
			ptemp=ptemp.next;
		}
		System.out.println(sb);
	}
	
	public void initNode(MyDoubleLinkedList<Integer> myDoubleLinkedList)
	{
		myDoubleLinkedList.addNode(new Node<Integer>(1));
		myDoubleLinkedList.addNode(new Node<Integer>(2));
		myDoubleLinkedList.addNode(new Node<Integer>(3));
		show(myDoubleLinkedList);
	}
	public  void addNodeTest(MyDoubleLinkedList<Integer> linkedList)
	{
		linkedList.addNode(3,new Node<Integer>(13));
		linkedList.addNode(5,new Node<Integer>(15));
		linkedList.addNode(6,new Node<Integer>(16));
		linkedList.addNode(4,new Node<Integer>(14));
		linkedList.addNode(2,new Node<Integer>(12));
		linkedList.addNode(8,new Node<Integer>(19));
		linkedList.addNode(7,new Node<Integer>(17));
		show(linkedList);
	}
}



public class DoublyLinkedList
{
	public static void main(String[] args)
	{
		MyDoubleLinkedList<Integer>myDoubleLinkedList=new MyDoubleLinkedList<Integer>();
		myDoubleLinkedList.initNode(myDoubleLinkedList);
		myDoubleLinkedList.addNodeTest(myDoubleLinkedList);
	}
}
