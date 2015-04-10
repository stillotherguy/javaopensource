package com.rabbit.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;
import static org.junit.Assert.*;

import org.junit.Test;

public class CglibTest {
	@Test
	public void testFixedValueProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SimpleClass.class);
		enhancer.setCallback(new FixedValue() {

			@Override
			public Object loadObject() throws Exception {
				return "Hello cglib!";
			}

		});
		SimpleClass proxy = (SimpleClass) enhancer.create();
		assertEquals("Hello cglib!", proxy.echo(null));
	}

	@Test
	public void testInvocationHandler() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SimpleClass.class);
		enhancer.setCallback(new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
					return "Hello cglib!";
				}
				throw new RuntimeException("error");
			}

		});
		SimpleClass proxy = (SimpleClass) enhancer.create();
		assertEquals("Hello cglib!", proxy.echo(null));
		assertNotEquals("Hello cglib!", proxy.toString());
	}

	@Test
	public void testMethodInterceptor() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SimpleClass.class);
		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
					return "Hello cglib!";
				}
				return methodProxy.invokeSuper(object, args);
			}
		});
		SimpleClass proxy = (SimpleClass) enhancer.create();
		assertEquals("Hello cglib!", proxy.echo(null));
		assertNotEquals("Hello cglib!", proxy.toString());
		proxy.hashCode(); // Does not throw an exception or result in an endless
							// loop.
	}

	@Test
	public void testCallbackFilter() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SimpleClass.class);
		CallbackHelper helper = new CallbackHelper(SimpleClass.class, new Class[0]) {

			@Override
			protected Object getCallback(Method method) {
				if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
					return new FixedValue() {
						@Override
						public Object loadObject() throws Exception {
							return "Hello cglib!";
						};
					};
				} else {
					return NoOp.INSTANCE; // A singleton provided by NoOp.
				}
			}
		};
		enhancer.setCallbackFilter(helper);
		enhancer.setCallbacks(helper.getCallbacks());
		SimpleClass proxy = (SimpleClass) enhancer.create();
		assertEquals("Hello cglib!", proxy.echo(null));
		assertNotEquals("Hello cglib!", proxy.toString());
		proxy.hashCode(); // Does not throw an exception or result in an endless
							// loop.
	}
}
