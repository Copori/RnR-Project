package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.ResponseDto;
import com.example.bookrecommend.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    /** 책 상세페이지 */
    @GetMapping("/books/{bookId}/detail")
    public ResponseDto bookDetail(@PathVariable Long bookId) {
        return null;
    }

}
