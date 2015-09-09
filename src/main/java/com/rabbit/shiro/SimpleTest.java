package com.rabbit.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.springframework.util.Assert;

public class SimpleTest {
	
	@Test
	public void simple() {
		Factory<org.apache.shiro.mgt.SecurityManager> sm = new IniSecurityManagerFactory("classpath:shiro.ini"); 
		
		SecurityUtils.setSecurityManager(sm.getInstance());
		Subject subject = SecurityUtils.getSubject();
		//UnknownAccountException
		UsernamePasswordToken token = new UsernamePasswordToken("Ethan", "123");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		Assert.isTrue(subject.isAuthenticated());
		
		subject.logout();
	}
	
	@Test
	public void poly() {
		new Son().print();
		new Parent().print();
	}
	
	private static class Parent {
		public Parent() {
			System.out.println(this.getClass());
		}
		
		public void print() {
			System.out.println(getClass());
			System.out.println(this);
		}
	}
	
	private static class Son extends Parent {
		public Son() {
			System.out.println(getClass());
		}
	}
}
