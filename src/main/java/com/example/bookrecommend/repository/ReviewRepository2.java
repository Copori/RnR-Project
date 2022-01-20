package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * 1. 해당 Repository에서는 리뷰 목록을 paging처리하는 작업 진행
 * 2. 현재 SpringDataJPA로 처리할 여유가 없기에
 * 3. TODO ReviewRepository와 ReviewRepository2를 합칠 예정
 *
 */
@Repository
@RequiredArgsConstructor
public class ReviewRepository2 {

    private final EntityManager em;

    /** 
     * 리뷰 목록 페이징
     */ 
    public Page<Review> findReviews(Long bookId, Pageable pageable) {
        // 쿼리 생성
        List<Review> reviews = em.createQuery("select r " +
                        " from Review r" +
                        " where r.bookId = :bookId" +
                        " and r.activated = true" +
                        " order by r.id desc", Review.class)
                .setParameter("bookId", bookId)
                .getResultList();

        // list 사이즈
        int total = reviews.size();

        // PageImple 리턴
        return new PageImpl<>(reviews, pageable, total);

    }

}
