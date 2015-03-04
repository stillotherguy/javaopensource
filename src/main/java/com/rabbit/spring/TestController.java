/**
 * 
 */
package com.rabbit.spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ethan
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping
	@ResponseStatus(HttpStatus.OK)
	public void index() {
		//
	}
}
