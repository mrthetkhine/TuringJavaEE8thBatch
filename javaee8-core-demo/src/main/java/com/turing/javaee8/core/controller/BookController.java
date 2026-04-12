package com.turing.javaee8.core.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turing.javaee8.core.bean.common.BookValidator;
import com.turing.javaee8.core.dto.BookDto;
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
	
	@Autowired
	BookValidator bookValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(bookValidator);
	}

	
	@GetMapping
	String getAllBooks(Model model)
	{
		log.info("BookController.getAllBook ");
		List<BookDto> books = this.bookService.getAllBooks();
		model.addAttribute("books", books);
		return "books/index";
	}
	
	@GetMapping("/new")
	String newBook(Model model)
	{
		log.info("BookController.getAllBook ");
		BookDto book= new BookDto();
		book.setTitle("Default");
		model.addAttribute("book", book);
		return "books/new";
	}
	/*
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
		
	}*/
	@PostMapping("/new")
	String saveBook(Model model,@ModelAttribute("book") @Validated BookDto book,BindingResult result)
	{
		if(result.hasErrors())
		{
			log.info("have error in new ");
			model.addAttribute("book", book);
			result.getAllErrors().forEach(error->{
				log.info("code "+error.getCode()+" "+ error.getDefaultMessage());
			});
			return "books/new";
		}
		else
		{
			log.info("save Book "+book);
			this.bookService.saveBook(book);
			
			return "redirect:/books";
		}
		
	}
	@GetMapping("/delete/{id}")
	String deleteBook(@PathVariable Long id,Model model)
	{
		log.info("Delete book by Id " +id);
		this.bookService.deleteBookById(id);
		model.addAttribute("message", "Book with id "+id +" successfully deleted");
		
		List<BookDto> books = this.bookService.getAllBooks();
		model.addAttribute("books", books);
		return "books/index";
	}
	@GetMapping("/edit/{id}")
	String editBook(@PathVariable Long id,Model model)
	{
		log.info("edit book by Id " +id);
		Optional<BookDto> book = this.bookService.getBookById(id);
		
		model.addAttribute("book", book.get());
		return "books/new";
	}
	@PostMapping("/edit/{id}")
	String updateBook(@PathVariable Long id,Model model,@ModelAttribute("book") @Validated BookDto book,BindingResult result)
	{
		log.info("edit book by Id " +id);
		if(result.hasErrors())
		{
			model.addAttribute("book", book);
			return "books/new";
		}
		else
		{
			log.info("update Book "+book);
			this.bookService.updateBook(book);
			model.addAttribute("message", "Book with id "+id +" successfully updated");
			
			List<BookDto> books = this.bookService.getAllBooks();
			model.addAttribute("books", books);
			return "books/index";
		}
		
	}
}
