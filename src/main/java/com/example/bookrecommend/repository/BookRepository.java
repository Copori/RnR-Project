package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository{

    private final EntityManager em;

    public List<Review> findReviews(Long bookId) {
        // 쿼리 생성
        List reviews = em.createQuery("select r " +
                        " from Review r" +
                        " where r.bookId = :bookId" +
                        " and r.activated = true" +
                        " order by r.id desc")
                .setParameter("bookId", bookId)
                .getResultList();

        return reviews;

        // 총 사이즈 조회 => toal
    }

    // 좋아요 총 개수

    // 댓글 목록 조회
    // findAllById
}

