package com.example.BookOrder;

import com.example.BookOrder.dto.BookOrder;
import com.example.BookOrder.dto.BookOrderRequest;
import com.example.BookOrder.exception.NoBookIDException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    ResponseEntity<List<BookOrder>> receiveOrder(List<BookOrderRequest> orderRequests) {
        orderRequests.forEach(book -> {
            if (book.getViewCount() > 9) {
                if (book.getBookId() == null)
                    throw new NoBookIDException();
                BookOrder bo = orderRepository.findById(book.getBookId()).orElse(new BookOrder());
                bo.setBookId(book.getBookId());
                bo.setOrderAmount((int) book.getViewCount() / 10);
                orderRepository.save(bo);
            }
        });
        return ResponseEntity.ok(orderRepository.findAll());
    }

    public void exportOrder(HttpServletResponse response) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());


            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);
            Paragraph paragraph = new Paragraph("Order", fontTitle);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);

            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA);
            fontBody.setSize(12);

            orderRepository.findAll().forEach(o -> {
                Paragraph orderParagraph = new Paragraph(
                        String.format("Book of ID: %s, in amount of %s", o.getBookId(), o.getOrderAmount()),
                        fontBody);
                orderParagraph.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(orderParagraph);
            });

            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
