package br.com.book_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.book_service.model.Book;
import br.com.book_service.proxy.CambioProxy;
import br.com.book_service.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint")  // implementação Swagger
@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	
	@Operation(summary = "Find a specifc book by your ID") // implementação Swagger 
	@GetMapping("/{id}/{currency}")
	public Book findBookById(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		
		var book = repository.getById(id);
		if ( book == null) throw new RuntimeException("Book not Found!");
		
		
		
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		
		var port = environment.getProperty("local.server.port");
		book.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnvironment());
		book.setPrice(cambio.getConvertedValue());
		
		return book;
		
	}
}
