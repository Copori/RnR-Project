package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Query("select review from Review review where reviewId = :reviewId")
//    Review findByReviewId(@Param("reviewId") Long reviewId);

    List<Review> findAllByBookIdOrderByIdDesc(Long bookId);
    /**특정 유저의 특정 책 리뷰 존재 유무 쿼리*/
    @Query(value = "select count(r)" +
            " from Review as r" +
            " inner join User as u on u.id = :userId" +
            " where r.bookId = :bookId" +
            " and r.activated = true")
     int countWithReviewByUserIdAndBookId(@Param("userId") long userId, @Param("bookId") long bookId);

    // https://docs.spring.io/spring-data/jpa/docs/current/refereSnce/html/#jpa.query-methods.at-query
//    @Query("SELECT r FROM Review r WHERE review_id = :review_id")
//    Review findByReviewId(@Param("review_id") Long review_id);

    /**
     *
     */
    Optional<Review> findByIdAndUserId(Long id, Long userId);
}
