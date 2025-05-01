package br.com.book_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Foo bar")  // implementação Swagger
@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);

	@Operation(summary = "Foo bar") // implementação Swagger
	@GetMapping("/foo-bar")
	@Bulkhead( name = "default" ) //while (1) {curl http://localhost:8765/book-service/foo-bar; sleep 0.1}
	public String fooBar() {
		
		logger.info("Request to foo-bar is received!");
			
		return "Foo-Bar!!!";
		
	}
	
	public String fallbackMethod(Exception ex) {
		 return "fallbackMethod foo-bar!!!";
	}
}
