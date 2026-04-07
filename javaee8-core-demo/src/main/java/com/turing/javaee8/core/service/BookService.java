package com.turing.javaee8.core.service;

import java.util.List;

import com.turing.javaee8.core.model.Book;

public interface BookService {
	List<Book> getAllBooks();
	void saveBook(Book book);
}
