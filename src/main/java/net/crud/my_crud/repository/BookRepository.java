package net.crud.my_crud.repository;

import net.crud.my_crud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT DISTINCT genre FROM Book WHERE genre IS NOT NULL")
    List <String> getGenres();
}
