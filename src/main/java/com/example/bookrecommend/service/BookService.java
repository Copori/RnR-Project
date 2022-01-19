package com.example.bookrecommend.service;

import com.example.bookrecommend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final LikeService likeService;
    private final BookService bookService;

    /** 책 전체 리뷰 조회 */
    public void 책전체조회() {

        // 1. 좋아요 총 개수 => likeService

        // 2. 책에대한 전체 리뷰 조회 => bookService

    }
}
