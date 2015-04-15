package com.rabbit.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.Comparator;

import com.rabbit.reflect.Outer.Inner;

/**
 * http://stackoverflow.com/questions/2460502/what-are-the-interets-of-synthetic-methods
 * @author Ethan Zhang
 *
 */
public class JMMTest {
	public static void main(String[] args) throws Exception {
		Outer outer = new Outer();
		Inner inner = outer.getInstance();
		inner.test();
		
		//Method method = Outer.class.getDeclaredMethod("access$0", Outer.class);
		//method.invoke(outer, outer);
		
		Method method1 = null;

		Method[] methods = Inner.class.getDeclaredMethods();
		for(int i =0; i < methods.length; i++) {
			if(methods[i].isSynthetic()) {
				method1 = methods[i];
			}
		}
		
//		int i = 0;
//		while ((method1 == null) && (i < Outer.class.getDeclaredMethods().length)) {
//		    if (Inner.class.getDeclaredMethods()[i].isSynthetic()) {
//		        method1 = Outer.class.getDeclaredMethods()[i];
//		    }
//		    i++;
//		}
		
		System.out.println(method1);
		if (method1 != null) {
		    try {
		        System.out.println(method1.invoke(null, new Outer()));
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	}
	
}

class Outer {
	private Object obj;
	
	void echo() {
		System.out.println("outer echo success");
		final String message = "hello";
		
		new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				String str = message;
				try {
					//System.out.println(this.getClass().getDeclaredField("val$message").get(this));
					for(Field f : this.getClass().getDeclaredFields()) {
						System.out.println(f.getName());
					}
					
					for(Method m : this.getClass().getDeclaredMethods()) {
						System.out.println(m.getName());
					}
					
					for(Field f : Outer.class.getDeclaredFields()) {
						System.out.println(f.getName());
					}
					
					for(Method m : Outer.class.getDeclaredMethods()) {
						System.out.println(m.getName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
			
		}.compare(new Object(), new Object());
	}
	
	static void echo1() {
		System.out.println("outer echo success");
	}
	
	public class Inner {
		void test() throws Exception {
			Field field = this.getClass().getDeclaredField("this$0");
			field.setAccessible(true);
			Method m1 = Outer.class.getDeclaredMethod("echo");
			m1.invoke(field.get(this), (Object[]) null);
			
			for(Field f : this.getClass().getDeclaredFields()) {
				System.out.println(f.getName());
			}
			
			for(Method m : this.getClass().getDeclaredMethods()) {
				System.out.println(m.getName());
			}
			
			for(Field f : Outer.class.getDeclaredFields()) {
				System.out.println(f.getName());
			}
			
			for(Method m : Outer.class.getDeclaredMethods()) {
				System.out.println(m.getName());
			}
		}
		
		void echo() {
			System.out.println("outer echo success");
		}
	}
	
	Inner getInstance() {
		return new Inner();
	}
}