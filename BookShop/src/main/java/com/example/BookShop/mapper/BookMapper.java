package com.example.BookShop.mapper;

import com.bookapi.openapi.model.Author;
import com.bookapi.openapi.model.BookCreateRequest;
import com.bookapi.openapi.model.BookResponse;
import com.example.BookShop.dto.Book;
import com.example.BookOrder.dto.BookOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;


@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "author", source = "author.id")
    BookResponse mapToBookResponse(Book book);

    @Mapping(target = "author.id", source = "author")
    Book mapToBook(BookCreateRequest bookCreateRequest);

    @Mapping(target = "bookId", source = "id")
    BookOrderRequest mapToBookOrderRequest(Book bookDto);
}
