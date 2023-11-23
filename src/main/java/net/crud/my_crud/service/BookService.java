package net.crud.my_crud.service;

import net.crud.my_crud.model.Book;
import net.crud.my_crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {


    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public List <Book> findByGenre(String genre1){
        List<Book> booksByGenreList = new ArrayList<>();
        List<Book> allBooks = bookRepository.findAll();
        for (Book book: allBooks){
            if (book.getGenre() != null && book.getGenre().equals(genre1)){
                booksByGenreList.add(book);
            }
        }
        return booksByGenreList;
    }

    public List<String> allGenres(){
        return bookRepository.getGenres();
    }



    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public Book save(String fileName, String contentType, byte[] data) {
        Book book = new Book();
        book.setContentType(contentType);
        book.setFileName(fileName);
        book.setData(data);
        bookRepository.save(book);
        return book;
    }





}
