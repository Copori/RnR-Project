package com.example.bookrecommend.controller.dto;

import com.example.bookrecommend.domain.Review;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReviewDto {

    @NotNull
    private long reviewId;

    @NotNull(message = "별점은 필수 입력입니다.")
    private int reviewScore;

    @NotEmpty(message = "내용은 필수 입력입니다.")
    @Size(min = 0, max = 500)
    private String reviewContent;

    public ReviewDto(Review review) {
        reviewId = review.getBookId();
        reviewScore = review.getReviewScore();
        reviewContent = review.getReviewContent();
    }
}
