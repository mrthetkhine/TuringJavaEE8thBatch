package com.turing.javaee8.core.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.turing.javaee8.core.model.Book;
import com.turing.javaee8.core.repository.BookDao;

@Repository
public class BookDaoImpl implements BookDao{

	List<Book> books = new ArrayList<Book>();
	
	BookDaoImpl()
	{
		
		this.books.add(new Book("Author1","Title1",2010));
		this.books.add(new Book("Author 2","Title 2",2020));
	}
	
	@Override
	public List<Book> getAllBooks() {
		
		return this.books;
	}

	@Override
	public void saveBook(Book book) {
		this.books.add(book);
		
	}

}
