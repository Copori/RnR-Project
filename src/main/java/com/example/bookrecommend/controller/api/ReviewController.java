package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.ResponseDto;
import com.example.bookrecommend.controller.dto.ReviewDto;
import com.example.bookrecommend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

//    /** 책의 전체 리뷰 목록 조회 */
//    @GetMapping("/books/{bookId}/reviews")
//    public ResponseDto getReviews(@PathVariable Long bookId) {
//
//        List<ReviewDto> reviewDtos = reviewService.selectAllReview(bookId);
//
//        return new ResponseDto(HttpStatus.OK.value(), reviewDtos);
//    }
}
