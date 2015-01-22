package com.rabbit.concurrent;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author Ethan
 *
 */
public class VolatileCachedFactorizer extends GenericServlet {
	
	private static final long serialVersionUID = 1L;
	
	private volatile OneValueCache cache = new OneValueCache(null, null);
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		BigInteger i = null;//extractFromRequest(req);
		BigInteger[] factors = cache.getFactors(i);
		if(factors == null) {
			factors = null;//factor(i);
			cache = new OneValueCache(i, factors);  //这一步利用了volatile获取语义以及不可变对象来确保线程安全和可见性
		}
		
		//encodeIntoResponse(res, factors);
	}
	
	private static final class OneValueCache {
		private final BigInteger lastNumber;
		private final BigInteger[] lastFactors;
		
		public OneValueCache(BigInteger i, BigInteger[] factors) {
			super();
			this.lastNumber = i;
			this.lastFactors = Arrays.copyOf(factors, factors.length);
		}
		
		public BigInteger[] getFactors(BigInteger i) {
			if(lastNumber == null || !lastNumber.equals(i)) {
				return null;
			}
			
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
		
	}
}
