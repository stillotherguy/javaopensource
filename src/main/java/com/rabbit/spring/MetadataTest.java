/**
 * 
 */
package com.rabbit.spring;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.StandardClassMetadata;
import org.springframework.core.type.StandardMethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.util.AntPathMatcher;

import com.google.common.base.Optional;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

/**
 * @author Ethan
 *
 */
public class MetadataTest {
	
	@Test
	public void testMethodMetadata() throws NoSuchMethodException, SecurityException {
		StandardMethodMetadata m = new StandardMethodMetadata(Object.class.getMethod("toString", (Class<?>[])null));
		System.out.println(m.getMethodName());
		System.out.println(m.getDeclaringClassName());
	}
	
	@Test
	public void testClassMetadate() {
		StandardClassMetadata c = new StandardClassMetadata(Optional.class);
		System.out.println(c.getClassName());
	}
	
	@Test
	public void testAnnotationMetadata() {
		StandardAnnotationMetadata a = new StandardAnnotationMetadata(TestController.class);
		System.out.println(a.getAllAnnotationAttributes("org.springframework.web.bind.annotation.RequestMapping"));
	}
	
	@Test
	public void testFilter() throws IOException {
		AssignableTypeFilter f = new AssignableTypeFilter(List.class);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		resolver.setPathMatcher(new AntPathMatcher());
		Resource[] resources = resolver.getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/**/*.class");
		MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resolver);
		for(Resource r : resources) {
			MetadataReader reader = readerFactory.getMetadataReader(r);
			if(f.match(reader, readerFactory)) {
				System.out.println(r.getFilename());
			}
		}
	}
	
	@Test
	public void guava() throws IOException {
		ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses().forEach(new Consumer<ClassInfo>() {

			@Override
			public void accept(ClassInfo t) {
				
			}
		});;
	}
}
