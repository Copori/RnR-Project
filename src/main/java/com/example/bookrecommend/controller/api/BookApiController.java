package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.MyReviewSummaryDto;
import com.example.bookrecommend.controller.dto.ResponseDto;
import com.example.bookrecommend.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BookApiController {

    private final BookService bookService;

    /** 책 상세페이지 */
    @GetMapping("/books/{bookId}/detail")
    public ResponseDto bookDetail(@PathVariable Long bookId, Pageable pageable) {

        // 1. 현재 접속한 유저 추출
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 현재 로그인한 username
        String username = auth.getName();
        Object principal = auth.getPrincipal();

        log.info("현재 로그인한 user_name : {}", username);
        log.info("현재 로그인한 principal : {}", principal);

        MyReviewSummaryDto bookDetailInfo = bookService.findBookDetailInfo(bookId, pageable);

        return new ResponseDto(HttpStatus.OK.value(), bookDetailInfo);
    }
}
