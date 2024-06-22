package com.example.BookOrder.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "TITLE")
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String genre;
    private Double price;
    private Integer pages;
    @Column(name = "VIEWCOUNT")
    private Integer viewCount = 0;
    @Column(name = "AVAILABLECOPIES")
    private Integer availableCopies;
}
