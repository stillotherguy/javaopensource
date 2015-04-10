package com.rabbit.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) {
		Object obj = Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{EchoService.class}, new IncationHandler1());
		Object obj2 = Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{EchoService.class}, new IncationHandler2());
		System.out.println(obj.getClass());
	}
	
	private static class IncationHandler1 implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("proxy1");
			method.invoke(proxy, args);
			return null;
		}
		
	}
	
	private static class IncationHandler2 implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("proxy2");
			method.invoke(proxy, args);
			return null;
		}
		
	}

}
