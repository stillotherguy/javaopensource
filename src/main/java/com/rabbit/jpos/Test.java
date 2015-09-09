package com.rabbit.jpos;

import java.security.Provider;
import java.security.Security;


public class Test {
	public static void main(String[] args) {
		Provider[] providers = Security.getProviders();
		for(Provider p : providers) {
			System.out.println(p.getName());
		}
		System.out.println(providers);
	}
}
