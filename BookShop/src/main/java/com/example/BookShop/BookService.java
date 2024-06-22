package com.example.BookShop;

import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookOrder;
import com.bookapi.openapi.model.BookResponse;
import com.example.BookShop.dto.Author;
import com.example.BookShop.dto.Book;
import com.example.BookShop.exception.InvalidAuthorIdException;
import com.example.BookShop.exception.InvalidBookCreateRequestException;
import com.example.BookShop.exception.InvalidBookIdException;
import com.example.BookShop.feignClients.OrderClient;
import com.example.BookShop.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final OrderClient orderClient;

    public ResponseEntity<BookResponse> addBook(BookCreateRequest bookCreateRequest) {
        validateBookCreateRequest(bookCreateRequest);

        Book book = bookMapper.mapToBook(bookCreateRequest);
        book.setAuthor(this.getAuthorFormId(bookCreateRequest.getAuthor()));

        BookResponse body = bookMapper.mapToBookResponse(bookRepository.save(book));

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookResponse>> findByGenre(String genre) {
        return ResponseEntity.ok(bookRepository.findBooksByGenre(genre).stream().map(bookMapper::mapToBookResponse).toList());
    }

    public ResponseEntity<Void> deleteBook(UUID id) {
        validateBookId(id);

        bookRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<BookResponse> getBookById(UUID id) {
        validateBookId(id);

        Book book = bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        book.setViewCount(book.getViewCount() + 1);
        bookRepository.save(book);

        BookResponse body = bookMapper.mapToBookResponse(book);

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookResponse>> getBook() {
        List<BookResponse> body = bookRepository.findAll().stream().map(bookMapper::mapToBookResponse).toList();

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<BookResponse> updateBook(UUID id, BookCreateRequest bookCreateRequest) {
        validateBookId(id);
        validateBookCreateRequest(bookCreateRequest);

        Book book = bookRepository.getReferenceById(id);
        Book newBook = bookMapper.mapToBook(bookCreateRequest);
        newBook.setAuthor(this.getAuthorFormId(bookCreateRequest.getAuthor()));
        newBook.setId(book.getId());

        BookResponse body = bookMapper.mapToBookResponse(
                bookRepository.save(newBook
                ));

        return ResponseEntity.ok(body);
    }

    public ResponseEntity<List<BookOrder>> sendOrder() {
        return orderClient.sendOrder(bookRepository.findAll().stream().map(bookMapper::mapToBookOrderRequest).toList());
    }

    // Validation utilities
    private void validateBookCreateRequest(BookCreateRequest bookCreateRequest) {
        if (bookCreateRequest == null || bookCreateRequest.getAuthor() == null || bookCreateRequest.getAvailableCopies() == null || bookCreateRequest.getGenre() == null || bookCreateRequest.getPrice() == null || bookCreateRequest.getPages() == null)
            throw new InvalidBookCreateRequestException();
    }

    private void validateBookId(UUID uuid) {
        if (!bookRepository.existsById(uuid))
            throw new InvalidBookIdException();
    }

    // utils
    private Author getAuthorFormId(UUID uuid) {
        return authorRepository.findById(uuid).orElseThrow(InvalidAuthorIdException::new);
    }


    public Object purchaseBook(UUID id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new OpenApiResourceNotFoundException("Book not found"));
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            return bookRepository.save(book);
        } else {
            throw new OpenApiResourceNotFoundException("Book out of stock");
        }
    }
}
