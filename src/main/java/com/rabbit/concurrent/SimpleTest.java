package com.rabbit.concurrent;

public class SimpleTest {

	public static void main(String[] args) {
		new Thread(() -> {
			while(true) {
				//ignore
			}
		}).start();
	}

}
