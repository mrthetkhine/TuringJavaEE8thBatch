package com.turing.javaee8.jpamvc.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.turing.javaee8.jpamvc.model.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BookDaoTest {

	@Autowired
	BookDao bookDao;
	
	//@Test
	public void testGetAllBook()
	{
		List<Book> books = bookDao.findAll();
		for(Book book : books)
		{
			log.info("book "+book);
		}
		assertTrue(books.size()>0);
	
	}
	//@Test
	public void testFindById()
	{
		Optional<Book> book = this.bookDao.findById(3L);
		assertTrue(book.isPresent());
		
		Book data = book.get();
		log.info("Book "+data);
	}
	//@Test
	public void testSaveBook()
	{
		Book book = new Book();
		book.setTitle("Book 2");
		book.setAuthor("Author 2");
		book.setYear(2020);
		
		this.bookDao.save(book);
	}
	@Test
	public void testUpdate()
	{
		Optional<Book> result = this.bookDao.findById(2L);
		Book book = result.get();
		book.setTitle("Book Two");
		
		this.bookDao.save(book);
	}
	//@Test 
	public void testDelete()
	{
		this.bookDao.deleteById(7L);
	}
}
