package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select count(r)" +
            " from Review as r" +
            " inner join User as u on u.id = :userId" +
            " where r.bookId = :bookId" +
            " and r.activated = true")
     int countWithReviewByUserIdAndBookId(@Param("userId") long userId, @Param("bookId") long bookId);

    Optional<Review> findByIdAndUserId(Long id, Long userId);
}
