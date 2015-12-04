package com.team4.until;

import java.util.ArrayList;
import java.util.List;

public class ListUntil {
	//去除list中的重复元素
	public static <T> List<T> getNoRepeat(List<T> list,Class<T> listClass){
		List<T> returnList = new ArrayList<T>();
		if(list!=null){
			for(T i:list){
				if(!returnList.contains(i)){
					returnList.add(i);
				}
			}
		}
		return returnList;
	}

}
