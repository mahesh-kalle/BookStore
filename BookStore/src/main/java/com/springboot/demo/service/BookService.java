package com.springboot.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.demo.entity.Book;
import com.springboot.demo.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	public Book saveBook(Book book) {
		return repository.save(book);	
	}
	
	public List<Book> saveBooks(List<Book> books){
		return repository.saveAll(books);
	}
	
	public List<Book> getAllBooks() {
		return repository.findAll();
	}
	
	public Book getBookById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Book> getBooksByAuthor(String name){
		return repository.findByAuthor(name);
	}
	
	public List<Book> getBookByTitle(String title){
		return repository.findByName(title);
	}
	
	public String deleteBook(int id) {
		repository.deleteById(id);
		return "deleted book with id "+id;
	}
	
	public Book updateBook(Book book) {
		Book existingBook = repository.findById(book.getId()).orElse(null);
		existingBook.setName(book.getName());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setPrice(book.getPrice());
		return repository.save(existingBook);
	}
	
	
}
