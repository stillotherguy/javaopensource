package com.rabbit.jackson;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.TestBean;

public class TestCase {
	
	/**
	 * 同样的json deserialize不同的bean
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Test
	public void testDifferentDeserialize() throws JsonParseException, JsonMappingException, IOException {
		final String json = String.format("{\"code1\":%s,\"msg1\":\"%s\"}", 10010, "this is a test");
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TestBean t = mapper.readValue(json, TestBean.class);
		System.out.println(t);
	}
}
