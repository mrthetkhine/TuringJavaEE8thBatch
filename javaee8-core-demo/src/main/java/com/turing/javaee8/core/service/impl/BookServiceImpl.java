package com.turing.javaee8.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.javaee8.core.bean.common.Mapper;
import com.turing.javaee8.core.dto.BookDto;
import com.turing.javaee8.core.model.Book;
import com.turing.javaee8.core.repository.BookDao;
import com.turing.javaee8.core.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Autowired
	Mapper mapper;
	
	@Override
	public List<BookDto> getAllBooks() {
		
		List<Book> books = this.bookDao.getAllBooks();
		//List<BookDto> dtos = new ArrayList<>();
		/*
		for(Book b: books)
		{
			dtos.add(new BookDto(b.getId(),b.getTitle(),b.getAuthor(),b.getYear()));
		}*/
		return this.mapper.mapList(books, BookDto.class);
	}

	@Override
	public void saveBook(BookDto bookDto) {
		Book book = this.mapper.map(bookDto, Book.class);
		this.bookDao.saveBook(book);
		
	}

	@Override
	public void deleteBookById(long id) {
		this.bookDao.deleteBookById(id);
		
	}

	@Override
	public Optional<BookDto> getBookById(long id) {
		
		Optional<Book> result = this.bookDao.getBookById(id);
		if(result.isPresent())
		{
			BookDto dto = this.mapper.map(result.get(), BookDto.class);
			return Optional.of(dto);
		}
		else
		{
			return Optional.empty();
		}
	}

	@Override
	public void updateBook(BookDto bookDto) {
		Book book = this.mapper.map(bookDto, Book.class);
		this.bookDao.updateBook(book);
		
	}

	
}
