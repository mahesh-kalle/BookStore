package com.springboot.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.springboot.demo.entity.Book;
import com.springboot.demo.service.BookService;
import com.springboot.demo.repository.BookRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	private BookService service;
	
	@PostMapping("/createBook")
	public Book createBook(@RequestBody Book book) {
		return service.saveBook(book);
	}
	
	@PostMapping("/createTheBook")
	public ResponseEntity<Book> createTheBook(@RequestBody Book book) {
		try {
			Book _book = bookRepository
					.save(new Book(book.getName(), book.getAuthor(), book.getPrice()));
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(book, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/createBooks")
	public List<Book> createBooks(@RequestBody List<Book> books){
		return service.saveBooks(books);
	}

	@GetMapping("/retrieveBooks")
	public List<Book> retrieveBooks() {
		return service.getAllBooks();
	}
	
	@GetMapping("/retrieveBookById/{id}")
	public Book retrieveBookById(@PathVariable int id) {
		return service.getBookById(id);
	}
	
	@GetMapping("/retrieveBooksByAuthor/{name}")
	public List<Book> retrieveBooksByAuthor(@PathVariable String name){
		return service.getBooksByAuthor(name);
	}
	
	@GetMapping("/retrieveBookByTitle/{title}")
	public List<Book> retrieveBookByTitle(@PathVariable String title){
		return service.getBookByTitle(title);
	}
	
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
		try {
			List<Book> books = new ArrayList<Book>();

			if (title == null)
				bookRepository.findAll().forEach(books::add);
			else
				bookRepository.findByNameContaining(title).forEach(books::add);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@DeleteMapping("/deleteAllBooks")
	public ResponseEntity<HttpStatus> deleteAllBooks() {
		try {
			bookRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id) {
		return service.deleteBook(id);
	}
	
	@PutMapping("/updateBook")
	public Book updateBook(@RequestBody Book book) {
		return service.updateBook(book);
	}
	
}
