package com.rabbit.java8;

import java.util.Comparator;

public class LambdaAnalyze {
	public static void main(String[] args) {
		Runnable r = () -> {
			System.out.println("first lambda");
		};
		
		Comparator<Integer> c = (Integer i, Integer j) -> {
			return i - j;
		};
		r.run();
		c.compare(1, 2);
	}
}
