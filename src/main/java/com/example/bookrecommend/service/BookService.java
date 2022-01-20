package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.MyReviewSummaryDto;
import com.example.bookrecommend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BookService {

    private final LikeService likeService;
    private final ReviewService reviewService;

    /** 책 상세 페이지 */
    public MyReviewSummaryDto findBookDetailInfo(Long bookId, Pageable pageable) {

        // 1. 좋아요 총 개수 => likeService
        int likeCnt = likeService.selectBookCnt(bookId);
        log.info("likeCnt : {}s", likeCnt);

        // 2. 책에대한 전체 리뷰 조회 => bookService : 여기서 페이징처리를
        MyReviewSummaryDto myReviewSummaryDto = reviewService.selectAllReview(bookId, pageable);

        // 3. 좋아요 개수 추가
        myReviewSummaryDto.setLikeCnt(likeCnt);

        // 4. return
        return myReviewSummaryDto;
    }
}
