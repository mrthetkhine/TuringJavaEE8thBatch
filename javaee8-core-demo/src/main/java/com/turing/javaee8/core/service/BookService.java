package com.turing.javaee8.core.service;

import java.util.List;
import java.util.Optional;

import com.turing.javaee8.core.dto.BookDto;
import com.turing.javaee8.core.model.Book;

public interface BookService {
	List<BookDto> getAllBooks();
	void saveBook(BookDto book);
	void deleteBookById(long id);
	Optional<BookDto> getBookById(long id);
	void updateBook(BookDto book);
}
