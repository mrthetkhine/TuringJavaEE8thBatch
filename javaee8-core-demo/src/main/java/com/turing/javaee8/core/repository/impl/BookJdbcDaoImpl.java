package com.turing.javaee8.core.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.core.model.Book;
import com.turing.javaee8.core.repository.BookDao;

@Primary
@Repository
public class BookJdbcDaoImpl implements BookDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Book> getAllBooks() {
		return this.jdbcTemplate.query("SELECT * FROM books", this::mapRowToBook);
		
	}
	private Book mapRowToBook(ResultSet row, int rowNum)throws SQLException
	{
		return new Book(row.getLong("ID"),
						row.getString("title"),
						row.getString("author") ,
						row.getInt("year"));
	}
	@Override
	public void saveBook(Book book) {
		this.jdbcTemplate.update("INSERT INTO books (title,author,year)"
				+ " VALUES (?,?,?)", book.getTitle(), book.getAuthor(),book.getYear());
		
	}
	@Override
	public void deleteBookById(long id) {
		this.jdbcTemplate.update("DELETE FROM Books WHERE id=?",id);
		
	}
	@Override
	public Optional<Book> getBookById(long id) {
		List<Book> books = this.jdbcTemplate.query ("SELECT * FROM books WHERE id=?", 
				this::mapRowToBook,id);
		if(books.size()>0)
		{
			return Optional.of(books.get(0));
		}
		else
		{
			return Optional.empty();
		}
	}
	@Override
	public void updateBook(Book book) {
		
		this.jdbcTemplate.update("UPDATE books set title=?,author=?,year=?"
				+ " WHERE id=?", book.getTitle(), book.getAuthor(),book.getYear(),book.getId());
	}

}
