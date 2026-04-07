package com.turing.javaee8.core.repository;

import java.util.List;

import com.turing.javaee8.core.model.Book;

public interface BookDao {
	List<Book> getAllBooks();
	void saveBook(Book book);
}
