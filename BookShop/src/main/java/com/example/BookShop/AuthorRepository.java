package com.example.BookShop;

import com.example.BookShop.dto.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorRepository extends CrudRepository<Author, UUID> {
}
