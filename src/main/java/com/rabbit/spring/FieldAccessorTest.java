/**
 * 
 */
package com.rabbit.spring;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.PropertyAccessorFactory;

/**
 * @author Ethan
 *
 */
public class FieldAccessorTest {
	@Test
	public void testBeanWrapper() {
		Department department = new Department();
		department.setName("test");
		Department parent = new Department();
		parent.setName("parent");
		department.setParent(parent);
		BeanWrapper b = PropertyAccessorFactory.forBeanPropertyAccess(department);
		b.setPropertyValue("name", "test1");
		b.setPropertyValue("parent.name", "parent1");
		System.out.println(department.getName());
		System.out.println(department.getParent().getName());
	}
	
	/**
	 * no setter
	 */
	@Test
	public void testDirectAccess() {
		Department department = new Department();
		DirectFieldAccessor d = new DirectFieldAccessor(department);
		d.setPropertyValue("level", 5);
		System.out.println(department.getLevel());
	}
}
