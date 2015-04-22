package com.rabbit.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) {
		EchoService es = new EchoServiceImpl();
		EchoService obj = (EchoService) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{EchoService.class}, new IncationHandler1(es));
		obj.echo();
	}
	
	private static class IncationHandler1 implements InvocationHandler {
		private Object proxy;
		
		public IncationHandler1(Object proxy) {
			this.proxy = proxy;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println(proxy.getClass());
			method.invoke(this.proxy, args);
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
