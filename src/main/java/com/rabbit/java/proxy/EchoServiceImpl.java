package com.rabbit.java.proxy;

public class EchoServiceImpl implements EchoService {

	@Override
	public void echo() {
		System.out.println("original output");
	}

}
