package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.ReviewDto;
import com.example.bookrecommend.domain.Review;
import com.example.bookrecommend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /** 책의 전체 리뷰 목록 조회 */
    public List<ReviewDto> selectAllReview(Long bookId) {

        // 책의 전체 리뷰 목록 조회
        List<Review> reviews = reviewRepository.findAllByBookIdByOrderByIdDesc();

        // Entity -> dto
        List<ReviewDto> result = reviews.stream()
                .map(r -> new ReviewDto(r))
                .collect(Collectors.toList());

        return result;
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