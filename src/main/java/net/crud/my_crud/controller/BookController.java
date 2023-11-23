package net.crud.my_crud.controller;

import net.crud.my_crud.model.Book;
import net.crud.my_crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public String findAll(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        return "book-list";
    }

    @GetMapping("/books/{genre}")
    public String findBookByGenres(@PathVariable("genre") String genre, Model model){
        List<Book> books = bookService.findByGenre(genre);
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/genres")
    public String findGenres(Model model){
        List<String> genres = bookService.allGenres();
        model.addAttribute("genres", genres);
        return "genre-list";
    }



    @GetMapping ("/book-create")
    public String createBookForm(Book book){
        return "book-create";
    }

    @PostMapping ("/book-create")
    public String createBook(Book book){
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("book-delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("book-update/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "book-update";
    }

    @PostMapping("/book-update")
    public String updateBook(Book book){
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping ("/books")
    public String create(@RequestPart("data") MultipartFile file) throws IOException {

        Book book = bookService.save(file.getOriginalFilename(), file.getContentType(),file.getBytes());

        return "redirect:/books";
    }


}
