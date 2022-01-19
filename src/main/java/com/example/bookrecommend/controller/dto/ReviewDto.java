package com.example.bookrecommend.controller.dto;

import com.example.bookrecommend.domain.Review;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private long book_id;

    @NotEmpty
    private int reviewScore;

    @NotEmpty
    @Size(min = 0, max = 500)
    private String reviewContent;

    public ReviewDto(Review review) {
        book_id = review.getBookId();
        reviewScore = review.getReviewScore();
        reviewContent = review.getReviewContent();
    }
}
