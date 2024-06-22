package com.example.BookOrder;

import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookResponse;
import com.example.BookOrder.dto.Author;
import com.example.BookOrder.dto.Book;
import com.example.BookOrder.exception.InvalidBookCreateRequestException;
import com.example.BookOrder.exception.InvalidBookIdException;
import com.example.BookOrder.feignClients.OrderClient;
import com.example.BookOrder.mapper.BookMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private OrderClient orderClient;

    @InjectMocks
    private BookService bookService;

    private BookCreateRequest bookCreateRequest;
    private Book book;
    private BookResponse bookResponse;
    private UUID bookId;
    private UUID authorId;
    private Author author;

    @BeforeEach
    void setUp() {
        bookId = UUID.randomUUID();
        authorId = UUID.randomUUID();

        bookCreateRequest = new BookCreateRequest();
        bookCreateRequest.setAuthor(authorId);
        bookCreateRequest.availableCopies(10);
        bookCreateRequest.setPrice(29.99);
        bookCreateRequest.setPages(123);
        bookCreateRequest.setGenre("Fiction");

        author = new Author();
        author.setId(authorId);

        book = new Book();
        book.setId(bookId);
        book.setAuthor(author);

        bookResponse = new BookResponse();
        bookResponse.setId(bookId);
    }

    @Test
    void testAddBook() {
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(bookMapper.mapToBook(any(BookCreateRequest.class))).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookMapper.mapToBookResponse(any(Book.class))).thenReturn(bookResponse);

        ResponseEntity<BookResponse> response = bookService.addBook(bookCreateRequest);

        assertNotNull(response);
        assertEquals(bookId, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void testAddBookInvalidRequest() {
        bookCreateRequest.setAuthor(null);

        assertThrows(InvalidBookCreateRequestException.class, () -> bookService.addBook(bookCreateRequest));
    }

    @Test
    void testFindByGenre() {
        List<Book> books = List.of(book);
        when(bookRepository.findBooksByGenre("Fiction")).thenReturn(books);
        when(bookMapper.mapToBookResponse(book)).thenReturn(bookResponse);

        ResponseEntity<List<BookResponse>> response = bookService.findByGenre("Fiction");

        assertNotNull(response);
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(bookId)).thenReturn(true);

        ResponseEntity<Void> response = bookService.deleteBook(bookId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void testDeleteBookInvalidId() {
        when(bookRepository.existsById(bookId)).thenReturn(false);

        assertThrows(InvalidBookIdException.class, () -> bookService.deleteBook(bookId));
    }

    @Test
    void testGetBook() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(bookMapper.mapToBookResponse(book)).thenReturn(bookResponse);

        ResponseEntity<BookResponse> response = bookService.getBookById(bookId);

        assertNotNull(response);
        assertEquals(bookId, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void testGetBookInvalidId() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        when(bookRepository.existsById(bookId)).thenReturn(true);

        assertThrows(InvalidBookIdException.class, () -> bookService.getBookById(bookId));
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.mapToBookResponse(book)).thenReturn(bookResponse);

        ResponseEntity<List<BookResponse>> response = bookService.getBook();

        assertNotNull(response);
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }


}