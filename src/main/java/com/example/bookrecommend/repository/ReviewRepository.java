package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Query("select review from Review review where reviewId = :reviewId")
//    Review findByReviewId(@Param("reviewId") Long reviewId);

    List<Review> findAllByBookIdOrderByIdDesc(Long bookId);

    // https://docs.spring.io/spring-data/jpa/docs/current/refereSnce/html/#jpa.query-methods.at-query
//    @Query("SELECT r FROM Review r WHERE review_id = :review_id")
//    Review findByReviewId(@Param("review_id") Long review_id);


}
