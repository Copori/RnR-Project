package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.ResponseDto;
import com.example.bookrecommend.controller.dto.ReviewDto;
import com.example.bookrecommend.domain.Review;
import com.example.bookrecommend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    /** 책의 전체 리뷰 목록 조회 */
    @GetMapping("/books/{bookId}/details")
    public ResponseDto getReviews(@PathVariable Long bookId) {

        List<ReviewDto> reviewDtos = reviewService.selectAllReview(bookId);

        return new ResponseDto(HttpStatus.OK.value(), reviewDtos);
    }

    /** 리뷰 생성 */
    @PostMapping("/reviews")
    public String postReview(@Valid @RequestBody ReviewDto request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        Review review = reviewService.saveReview(username,request);

        return "요청에 성공하였습니다.";
    }

    /** 리뷰 수정 */
    @PatchMapping("/reviews/{reviewId}")
    public String patchReview(@PathVariable Long reviewId, @RequestBody ReviewDto request) {

        // 리뷰 수정
        reviewService.updateReview(reviewId, request);

        //Entity->Dto
        return "수정이 성공하였습니다.";
    }


    /** 리뷰 삭제 */
//    @PutMapping("/reviews/{reviewId}")
//    public String deleteReview(@PathVariable Long reviewId, @RequestBody ReviewDto request) {
//
//        // 리뷰 수정
//        reviewService.deleteReview(reviewId, request);
//
//        //Entity->Dto
//        return "삭제가 성공하였습니다.";
//    }
    /**requestBody안 받아도 될 거 같아서 뺐음 -> 잘 작동함*/
    @PutMapping("/reviews/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {

        // 리뷰 수정
        reviewService.deleteReview(reviewId);

        //Entity->Dto
        return "삭제가 성공하였습니다.";

    }
}
