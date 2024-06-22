package com.example.BookShop.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @UuidGenerator
    private UUID id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String genre;
    private Double price;
    private Integer pages;
    @Column(name = "viewcount")
    private Integer viewCount = 0;
    @Column(name = "availablecopies")
    private Integer availableCopies;
}
