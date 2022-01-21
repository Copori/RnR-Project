package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.ResponseDto;
import com.example.bookrecommend.controller.dto.ReviewDto;
import com.example.bookrecommend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
public class ReviewApiController {

    private final ReviewService reviewService;

    /** 리뷰 생성 */
    @PostMapping("/reviews")
    public ResponseDto postReview(@Valid @RequestBody ReviewDto request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        reviewService.saveReview(username, request);

        return new ResponseDto(HttpStatus.OK.value());
    }

    /** 리뷰 수정 */
    @PutMapping("/reviews/{reviewId}")
    public ResponseDto patchReview(@PathVariable Long reviewId, @RequestBody ReviewDto request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        log.info("request: {}", request);
        // 리뷰 수정
        reviewService.updateReview(reviewId, request, username);

        //Entity->Dto
        return new ResponseDto(HttpStatus.OK.value());
    }


    /** 리뷰 삭제 */
    @PutMapping("/reviews/cancel/{reviewId}")
    public ResponseDto deleteReview(@PathVariable Long reviewId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        // 리뷰 수정
        reviewService.deleteReview(reviewId, username);

        //Entity->Dto
        return new ResponseDto(HttpStatus.OK.value());
    }
}
