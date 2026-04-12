package com.turing.javaee8.core.repository;

import java.util.List;
import java.util.Optional;

import com.turing.javaee8.core.model.Book;

public interface BookDao {
	List<Book> getAllBooks();
	void saveBook(Book book);
	void deleteBookById(long id);
	Optional<Book> getBookById(long id);
	void updateBook(Book book);
}
