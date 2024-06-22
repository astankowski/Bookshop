package com.example.BookShop;

import com.example.BookShop.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT b FROM Book b WHERE UPPER(b.genre) like UPPER(:genre)")
    List<Book> findBooksByGenre(@Param("genre") String genre);
}
