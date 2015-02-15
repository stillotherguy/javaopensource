/**
 * 
 */
package com.rabbit.spring;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Ethan
 *
 */
public class BeanWrapperTest {
	@Test
	public void test() {
		Department department = new Department();
		department.setName("test");
		Department parent = new Department();
		parent.setName("parent");
		department.setParent(parent);
		BeanWrapper b = new BeanWrapperImpl(department);
		b.setPropertyValue("name", "test1");
		b.setPropertyValue("parent.name", "parent1");
		System.out.println(department.getName());
		System.out.println(department.getParent().getName());
	}
}
