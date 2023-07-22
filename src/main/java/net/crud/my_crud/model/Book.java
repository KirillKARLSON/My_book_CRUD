package net.crud.my_crud.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "author")
    private String author;

    @Column (name = "book_name")
    private String book_name;
}
