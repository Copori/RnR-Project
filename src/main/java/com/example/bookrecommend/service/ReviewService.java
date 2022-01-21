package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.MyReviewSummaryDto;
import com.example.bookrecommend.controller.dto.ReviewDto;
import com.example.bookrecommend.domain.Review;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.repository.ReviewRepository;
import com.example.bookrecommend.repository.ReviewRepository2;
import com.example.bookrecommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReviewRepository2 reviewRepository2;

    /**
     * 책의 전체 리뷰 목록 조회
     */
    public MyReviewSummaryDto selectAllReview(Long bookId, Pageable pageable) {

        // TODO 책 리뷰 목록을 페이징 처리해서 받아야됨
        // TODO reviewRepository2를 새로 파야됨

        // 페이징 처리된 결과 return
        Page<Review> reviews = reviewRepository2.findReviews(bookId, pageable);

        // dto로
        List<ReviewDto> reviewDtos = reviews.stream()
                .map(r -> ReviewDto.builder()
                        .reviewId(r.getId())
                        .bookId(r.getBookId())
                        .reviewScore(r.getReviewScore())
                        .reviewContent(r.getReviewContent())
                        .build())
                .collect(Collectors.toList());

        // dto 총 개수 반환
        int total = reviewDtos.size();

        // 평균 값 계산
        Double reviewAvg = reviewDtos.stream()
                .collect(Collectors.averagingInt(n -> n.getReviewScore()));

        //TODO 리뷰 평점 체크
        return new MyReviewSummaryDto(0, reviewDtos, total, reviewAvg);
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
    public void saveReview(String username, ReviewDto reviewDto) {

        // 유저 네임으로 현재 userId를 찾아옴
        Optional<User> findUser = userRepository.findByUsername(username);

        // findId 찾아오기
        User user = findUser.get();
        Long userId = user.getId();

        // bookId추출
        long bookId = reviewDto.getBookId();

        // 현재 user가 해당 bookId에 대해 첫 댓글인지
        int reviewSaveCnt = reviewRepository.countWithReviewByUserIdAndBookId(userId, bookId);// null or 1

        if( reviewSaveCnt > 0 ) {
            // 댓글 달았던 이력이 있는 경우
            throw new IllegalStateException("이미 댓글을 작성한 회원입니다.");
        }else {
            // 댓글 달았던 이력이 없을 경우
            Review review = Review.builder()
                    .bookId(reviewDto.getBookId())
                    .user(user)
                    .reviewScore(reviewDto.getReviewScore())
                    .reviewContent(reviewDto.getReviewContent())
                    .activated(true)
                    .build();
            reviewRepository.save(review);

        }
    }

    /** 리뷰 수정 */
    @Transactional
    public void updateReview(Long id, ReviewDto request, String username){

        // 유저 네임으로 현재 userId를 찾아옴
        Optional<User> findUser = userRepository.findByUsername(username);

        // findId 찾아오기
        User user = findUser.get();
        Long userId = user.getId();

        // 요청 객체 추출
        Optional<Review> findReview = reviewRepository.findByIdAndUserId(id, userId);

        // 추출한 객체에 수정 요청으로 들어온 값 세팅
        if (findReview.isPresent()) {
            Review review = findReview.stream().findFirst().get();
            review.setReviewScore(request.getReviewScore());
            review.setReviewContent(request.getReviewContent());
        }

    }
    /** 리뷰 삭제 */
    @Transactional
    public void deleteReview(Long id, String username){

        // 유저 네임으로 현재 userId를 찾아옴
        Optional<User> findUser = userRepository.findByUsername(username);

        // findId 찾아오기
        User user = findUser.get();
        Long userId = user.getId();

        // 요청 객체 추출
        Optional<Review> findReview = reviewRepository.findByIdAndUserId(id, userId);

        // 추출한 객체에 수정 요청으로 들어온 값 세팅
        if (findReview.isPresent()) {
            Review review = findReview.stream().findFirst().get();
            review.setActivated(false);
        }
    }
}