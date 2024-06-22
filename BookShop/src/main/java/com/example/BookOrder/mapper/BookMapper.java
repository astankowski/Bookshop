package com.example.BookOrder.mapper;

import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookResponse;
import com.example.BookOrder.dto.Book;
import com.example.BookOrder.dto.BookOrderRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse mapToBookResponse(Book book);

    BookResponse mapToBookResponse(BookCreateRequest bookCreateRequest);

    Book mapToBook(BookCreateRequest bookCreateRequest);

    Book mapToBook(BookResponse bookResponse);

    BookCreateRequest mapToBookCreateRequest(Book bookDto);

    BookCreateRequest mapToBookCreateRequest(BookResponse bookResponse);

    BookOrderRequest mapToBookOrderRequest(Book bookDto);
}
