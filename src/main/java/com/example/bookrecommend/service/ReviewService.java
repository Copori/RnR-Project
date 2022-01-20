package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.ReviewDto;
import com.example.bookrecommend.domain.Review;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.repository.ReviewRepository;
import com.example.bookrecommend.repository.UserRepository;
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
    private final UserRepository userRepository;

    /**
     * 책의 전체 리뷰 목록 조회
     */
    public List<ReviewDto> selectAllReview(Long bookId) {

        // 책의 전체 리뷰 목록 조회
        List<Review> reviews = reviewRepository.findAllByBookIdOrderByIdDesc(bookId);

        // Entity -> dto
        List<ReviewDto> result = reviews.stream()
                .map(r -> new ReviewDto(r))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * 리뷰 ID 별 리뷰 조회
     */
    public Review selectReview(Long reviewId) {

        Optional<Review> findReview = reviewRepository.findById(reviewId);

        Review returnReview = findReview.stream().findFirst().orElseGet(() -> {
            return new Review();
        });

        return returnReview;
    }


    /**
     * 리뷰 작성
     */
    @Transactional
    public Review saveReview(String username, ReviewDto reviewDto) {
        // 유저 네임으로 현재 userId를 찾아옴
        Optional<User> findUser = userRepository.findByUsername(username);

        // findId 찾아오기
        Long uerId = findUser.get().getId();

        // 권한정보를 포함해 리뷰를 생성.
        Review review = Review.builder()
                .bookId(reviewDto.getBookId())
                .userId(uerId)
                .reviewScore(reviewDto.getReviewScore())
                .reviewContent(reviewDto.getReviewContent())
                .activated(true)
                .build();

        // 유저정보 + 권한정보를 저장
        return reviewRepository.save(review);
    }

    /** 리뷰 수정 */
    @Transactional
    public void updateReview(Long id, ReviewDto request){

        // 요청 객체 추출
        Optional<Review> findReview = reviewRepository.findById(id);


        // 추출한 객체에 수정 요청으로 들어온 값 세팅
        if (findReview.isPresent()) {
            Review review = findReview.stream().findFirst().get();
            review.setReviewScore(request.getReviewScore());
            review.setReviewContent(request.getReviewContent());
        }

    }
    /** 리뷰 삭제 */
    @Transactional
    public void deleteReview(Long id){

        // 요청 객체 추출
        Optional<Review> findReview = reviewRepository.findById(id);


        // 추출한 객체에 수정 요청으로 들어온 값 세팅
        if (findReview.isPresent()) {
            Review review = findReview.stream().findFirst().get();
            review.setActivated(false);
        }

    }

}