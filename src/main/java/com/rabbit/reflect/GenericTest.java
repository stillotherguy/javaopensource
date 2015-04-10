package com.rabbit.reflect;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.rabbit.jackson.TestBean;

public class GenericTest<T extends Comparable<? super T>> {
	private T t;
	private List<Map> list;
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		System.out.println(GenericTest.class.getDeclaredField("list").toGenericString());
		
		List<? extends TestBean> list = Lists.newArrayList();
		TestBean t = list.get(0);
	}
	
	public static <T extends Comparable<? super TestBean>> T test(T t) {
		
		
		return t;
		
	}

}
