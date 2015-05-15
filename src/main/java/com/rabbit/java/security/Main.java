package com.rabbit.java.security;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;

public class Main {

	public static void main(String[] args) {
		Provider[] providers = Security.getProviders();
		for(Provider p : providers) {
			for(Service s : p.getServices()) {
				System.out.println(s.getAlgorithm() + "," + s.getClassName());
			}
		}
	}

}
