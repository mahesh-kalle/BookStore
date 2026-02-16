package com.springboot.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByAuthor(String name);
	List<Book> findByName(String title);
	List<Book> findByNameContaining(String title);
	
}
