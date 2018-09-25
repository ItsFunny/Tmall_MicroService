package com.joker.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Object a=new Integer[] {1,2,3,5};
    	Integer[] arr=(Integer[]) a;
    	List<Integer>list=new ArrayList<>();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(4);
    	System.out.println(arr);
    	
    }
}
