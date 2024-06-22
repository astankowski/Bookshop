package com.example.BookOrder.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookOrderRequest {
    private UUID bookId;
    private Integer quantity;
    private Integer viewCount;
}
