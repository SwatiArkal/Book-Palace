package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.book.service.BookService;
import com.book.entity.Book;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/admin_book_register")
	public String bookRegister() {
		return "adminBookRegister";
	}

	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book> list = bookService.getAllBook();
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("bookList");
//		mv.addObject("book", list);
//		return mv;
		return new ModelAndView("bookList", "book", list);
	}

	@GetMapping("/manage_books")
	public ModelAndView getAllBooksAdmin() {
		List<Book> list = bookService.getAllBook();
		return new ModelAndView("adminBookList", "book", list);
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		bookService.save(b);
		return "redirect:/manage_books";
	}

	@RequestMapping("/adminEditBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "adminBookEdit";
	}

	@RequestMapping("/adminDeleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
		return "redirect:/manage_books";
	}

}
