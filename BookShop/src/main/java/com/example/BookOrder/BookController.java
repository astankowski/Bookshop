package com.example.BookOrder;

import com.bookapi.openapi.api.BookControllerApi;
import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookOrder;
import com.bookapi.openapi.model.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController implements BookControllerApi {
    @Autowired
    private final BookService service;

    @GetMapping("/getBook/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) {
        return service.getBookById(id);
    }

    @GetMapping("/getBook")
    public ResponseEntity<List<BookResponse>> getBook() {
        return service.getBook();
    }

    @GetMapping("/getBookByGenre/{gen}")
    public ResponseEntity<List<BookResponse>> getBookByGenre(@PathVariable String gen) {
        return service.findByGenre(gen);
    }

    @GetMapping("/orderBooks")
    public ResponseEntity<List<BookOrder>> orderBooks() {
        return service.sendOrder();
    }

    @PostMapping("/purchase/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookResponse> purchaseBook(@PathVariable UUID id) {
        return ResponseEntity.ok((BookResponse) service.purchaseBook(id));
    }

    @PostMapping("/addBook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookResponse> createBook(BookCreateRequest bookCreateRequest) {
        return service.addBook(bookCreateRequest);
    }

    @PutMapping("/updateBook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookResponse> updateBook(@PathVariable UUID id, BookCreateRequest bookCreateRequest) {
        return service.updateBook(id, bookCreateRequest);
    }

    @DeleteMapping("/deleteBook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        return service.deleteBook(id);
    }
}
