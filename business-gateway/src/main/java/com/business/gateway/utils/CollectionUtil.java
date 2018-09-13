package com.business.gateway.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe 集合操作
 * @author wupeng
 * @createtime  2017年7月17日
 */
public class CollectionUtil{
	
	/**
	 * 切割集合
	 * @author wupeng
	 * @param lists
	 * @param limit
	 * @return List<List<T>>
	 */
	public static <T> List<List<T>> splitList(List<T> lists, int limit){  
	    int size=lists.size();  
	      
	    List<List<T>> list=new ArrayList<List<T>>();  
	    if(limit>size){  
	        list.add(lists);  
	        return list;  
	    }  
	    int result=0;  
	    for(int i=0;i<size;i=i+limit){  
	        result=i+limit;  
	        if(result>size){  
	            result=size;  
	        }  
	        list.add(lists.subList(i, result));  
	    }  
	    return list;  
	}  

}
