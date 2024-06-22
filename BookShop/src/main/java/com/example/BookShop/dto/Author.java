package com.example.BookShop.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<Book> book;
}
