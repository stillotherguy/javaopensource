package com.rabbit.reorder;

import org.junit.Test;

public class ReorderShowCase {
	private boolean flag = false;
	private int d;
	
	/**
	 * 重排序的反例
	 */
	@Test
	public void reordercase1(){
		new Thread(){
			@Override
			public void run(){
				d = 3;
				//插入STORESTORE指令可以解决
				flag = true;
			}
		}.run();
		
		new Thread(){
			@Override
			public void run(){
				if(flag){
					int e = d;
				}
			}
		}.run();
	}
	
	@Test
	public void reordercase2(){
		//A1和A2可能会被重排序（除非插入LOADLOAD指令），但是A1、A2和A3都有数据依赖性，处理器和编译器都会保证A1、A2和A3不会重排序
		int a = 1;	//A1
		int b = 2;  //A2
		int c = a + b;	//A3
	}
}
