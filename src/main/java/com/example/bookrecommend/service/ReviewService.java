package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.MyReviewSummaryDto;
import com.example.bookrecommend.controller.dto.ReviewDto;
import com.example.bookrecommend.domain.Review;
import com.example.bookrecommend.repository.ReviewRepository;
import com.example.bookrecommend.repository.ReviewRepository2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewRepository2 reviewRepository2;

    /** 책의 전체 리뷰 목록 조회 */
    public MyReviewSummaryDto selectAllReview(Long bookId, Pageable pageable) {

        // TODO 책 리뷰 목록을 페이징 처리해서 받아야됨
        // TODO reviewRepository2를 새로 파야됨

        // 페이징 처리된 결과 return
        Page<Review> reviews = reviewRepository2.findReviews(bookId, pageable);

        log.info("reviews : {}", reviews);

        // dto로
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(r -> ReviewDto.builder()
                        .bookId(r.getId())
                        .reviewScore(r.getReviewScore())
                        .reviewContent(r.getReviewContent())
                        .build())
                .collect(Collectors.toList());

        // dto 총 개수 반환
        int total = reviewDtos.size();

        // 평균 값 계산
        Double reviewAvg = reviewDtos.stream()
                .collect(Collectors.averagingInt(n -> n.getReviewScore()));

        log.info("평균 값은 ? ", reviewAvg);


        //TODO 리뷰 평점 체크
        return new MyReviewSummaryDto(0, reviewDtos, total, reviewAvg);

        // 책의 전체 리뷰 목록 조회
        /*List<Review> reviews = reviewRepository.findAllByBookIdOrderByIdDesc(bookId);
        // Entity -> dto
        List<ReviewDto> result = reviews.stream()
                .map(r -> new ReviewDto(r))
                .collect(Collectors.toList());

        return result;*/
    }

    /** 리뷰 ID 별 리뷰 조회  */
    public Review selectReview(Long reviewId) {

        Optional<Review> findReview = reviewRepository.findById(reviewId);

        Review returnReview = findReview.stream().findFirst().orElseGet(() -> {
            return new Review();
        });

        return returnReview;
    }

    /** 리뷰 작성 */
    @Transactional
    public Review saveReview(ReviewDto reviewDto){
        // 권한정보를 포함해 리뷰를 생성.
        Review review = Review.builder()
                .reviewScore(reviewDto.getReviewScore())
                .reviewContent(reviewDto.getReviewContent())
                .activated(true)
                .build();

        // 유저정보 + 권한정보를 저장
        return reviewRepository.save(review);
    }


}