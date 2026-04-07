package com.turing.javaee8.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.core.model.Book;
import com.turing.javaee8.core.repository.BookDao;
import com.turing.javaee8.core.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Override
	public List<Book> getAllBooks() {
		
		return this.bookDao.getAllBooks();
	}

	@Override
	public void saveBook(Book book) {
		this.bookDao.saveBook(book);
		
	}

}
