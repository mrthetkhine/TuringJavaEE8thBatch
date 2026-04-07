package com.turing.javaee8.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turing.javaee8.core.model.Book;
import com.turing.javaee8.core.service.BookService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping
	String getAllBooks(Model model)
	{
		log.info("BookController.getAllBook ");
		List<Book> books = this.bookService.getAllBooks();
		model.addAttribute("books", books);
		return "books/index";
	}
	
	@GetMapping("/new")
	String newBook(Model model)
	{
		log.info("BookController.getAllBook ");
		Book book= new Book();
		book.setTitle("Default");
		model.addAttribute("book", book);
		return "books/new";
	}
	@PostMapping("/new")
	String saveBook(Model model,@Valid Book book,BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("book", book);
			return "books/new";
		}
		else
		{
			log.info("save Book "+book);
			this.bookService.saveBook(book);
			
			return "redirect:/books";
		}
		
	}
}
