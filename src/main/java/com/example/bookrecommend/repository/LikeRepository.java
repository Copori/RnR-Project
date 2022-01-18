package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    /** 특정 유저가 좋아요 한 책 리스트 */
    public List<Like> findLikeAllByUserId(Long userId) {
        List<Like> likeAll = em.createQuery("" +
                        "SELECT l.bookId" +
                        " FROM Like l" +
                        " WHERE l.userId = :userId", Like.class)
                .setParameter("userId", userId)
                .getResultList();

        return likeAll;
    }

    /** 책기준 좋아요 개수 */
    public List<Like> findLikeCnt(Long bookId) {
        List<Like> totalLike = em.createQuery(
                        "SELECT l" +
                                " FROM Like l" +
                                " where l.bookId = :bookId", Like.class)
                .setParameter("bookId", bookId)
                .getResultList();

        return totalLike;
    }
}
