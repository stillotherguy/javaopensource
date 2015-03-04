/**
 * 
 */
package com.rabbit.spring;

import java.io.IOException;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

/**
 * @author Ethan
 *
 */
public class ResourceLoaderTest {
	
	@Test
	public void testDefault() {
		ResourceLoader defaultLoader = new DefaultResourceLoader();
		Resource r = defaultLoader.getResource("classpath:log4j.properties");
		Assert.isTrue(r.exists());
	}
	
	@Test
	public void testPattern() throws IOException {
		PathMatchingResourcePatternResolver patternLoader = new PathMatchingResourcePatternResolver();
		patternLoader.setPathMatcher(new AntPathMatcher());
		Resource[] resources = patternLoader.getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "META-INF/INDEX.LIST");
		System.out.println(resources.length);
	}
}
