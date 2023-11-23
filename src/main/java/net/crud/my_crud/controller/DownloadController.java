package net.crud.my_crud.controller;

import net.crud.my_crud.model.Book;
import net.crud.my_crud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {
        @Autowired
        private BookService bookService;



        @GetMapping("/download/{id}")
        public ResponseEntity<byte[]> download (@PathVariable Long id){

            Book book = bookService.findById(id);

            if (book == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            byte[] data = book.getData();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(book.getContentType()));
            ContentDisposition build = ContentDisposition.builder("attachment")
                    .filename(book.getFileName())
                    .build();
            headers.setContentDisposition(build);
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        }


}
