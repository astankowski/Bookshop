package com.example.BookOrder.feignClients;

import com.bookapi.openapi.model.BookOrder;
import com.example.BookOrder.dto.BookOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "order", url = "localhost:8080")
public interface OrderClient {
    @PostMapping("/order/sendOrder")
    ResponseEntity<List<BookOrder>> sendOrder(List<BookOrderRequest> orderRequests);
}
