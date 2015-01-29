/**
 * 
 */
package com.rabbit.java.classloader;

import org.junit.Test;

/**
 * @author Ethan
 *
 */
public class ClassLoaderTest {
	
	@Test
	public void testNoParent() throws ClassNotFoundException {
		NoParentClassLoader cl = new NoParentClassLoader();
		cl.testLoad();
	}
	
	
	
	private class NoParentClassLoader extends ClassLoader {
		public NoParentClassLoader() {
			super(null);
		}
		
		public void testLoad() throws ClassNotFoundException {
			@SuppressWarnings("unused")
			Class<?> clazz = loadClass("com.rabbit.java.classloader.ToLoad");
		}
	}
}
