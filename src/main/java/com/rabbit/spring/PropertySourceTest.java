/**
 * 
 */
package com.rabbit.spring;

import java.io.IOException;
import java.util.Map;











import org.junit.Test;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;

/**
 * @author Ethan
 *
 */
public class PropertySourceTest {
	
	@Test
	public void testps1() throws IOException {
		Map<String, Object> map = Maps.newHashMap();
		map.put("prop1", "value1");
		PropertySource mapps = new MapPropertySource("mapps", map);
		Assert.isTrue(mapps.containsProperty("prop1"));
		PropertySource resourceps = new ResourcePropertySource("resourceps", "classpath:resources.properties");
		Assert.isTrue(resourceps.containsProperty("LOG_LEVEL"));
	}
	
	@Test
	public void testps2() throws IOException {
		Map<String, Object> map = Maps.newHashMap();
		map.put("prop1", "value1");
		PropertySource mapps = new MapPropertySource("mapps", map);
		Assert.isTrue(mapps.containsProperty("prop1"));
		PropertySource resourceps = new ResourcePropertySource("resourceps", "classpath:resources.properties");
		Assert.isTrue(resourceps.containsProperty("LOG_LEVEL"));
		CompositePropertySource cps = new CompositePropertySource("cps");
		cps.addPropertySource(mapps);
		cps.addPropertySource(resourceps);
	}
	
	@Test
	public void testps3() throws IOException {
		Map<String, Object> map = Maps.newHashMap();
		map.put("prop1", "value1");
		PropertySource mapps = new MapPropertySource("mapps", map);
		Assert.isTrue(mapps.containsProperty("prop1"));
		PropertySource resourceps = new ResourcePropertySource("resourceps", "classpath:resources.properties");
		Assert.isTrue(resourceps.containsProperty("LOG_LEVEL"));
		MutablePropertySources mps = new MutablePropertySources();
		mps.addFirst(mapps);
		mps.addLast(resourceps);
		System.out.println(mps.get("mapps").getProperty("prop1"));
	}
	
	@Test
	public void testps4() throws IOException {
		Map<String, Object> map = Maps.newHashMap();
		map.put("prop1", "value1");
		PropertySource mapps = new MapPropertySource("mapps", map);
		Assert.isTrue(mapps.containsProperty("prop1"));
		PropertySource resourceps = new ResourcePropertySource("resourceps", "classpath:resources.properties");
		Assert.isTrue(resourceps.containsProperty("LOG_LEVEL"));
		MutablePropertySources mps = new MutablePropertySources();
		mps.addFirst(mapps);
		mps.addLast(resourceps);
		System.out.println(mps.get("mapps").getProperty("prop1"));
		
		PropertyResolver pr = new PropertySourcesPropertyResolver(mps);
		System.out.println(pr.resolvePlaceholders("there is a ${prop1} here"));
	}
	
	@Test
	public void testps5() {
		Environment en = new StandardEnvironment();
		System.out.println(en.getProperty("file.encoding"));
	}
}
