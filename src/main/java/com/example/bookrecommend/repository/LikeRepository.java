package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    /**
     * 특정 유저가 좋아요 누른 책 리스트
     */
    public List<Long> findLikeAllByUsername(String username) {
        List<Long> findList = em.createQuery("select l.bookId from Like l join l.user u where u.username =:username and l.activated = true", Long.class)
                .setParameter("username", username)
                .getResultList();

        return findList;
    }

    /**
     * 책기준 좋아요 개수
     */
    public List<Like> findLikeCnt(Long bookId) {
        List<Like> totalLike = em.createQuery(
                        "SELECT l" +
                                " FROM Like l" +
                                " where l.bookId = :bookId", Like.class)
                .setParameter("bookId", bookId)
                .getResultList();

        return totalLike;
    }

    /**
     * 좋아요 저장
     */
    public void LikeSaveAndCancel(Like like) {
        em.persist(like);
    }

    /**
     * 좋아요 업데이트
     */
    public Optional<Like> findByBookIdAndUserId(Long bookId, Long userId) {

        try {
            Like like = em.createQuery(
                            "select l " +
                                    " from Like l" +
                                    " join l.user u" +
                                    " where u.id =:userId" +
                                    " AND l.bookId=:bookId", Like.class)
                    .setParameter("bookId", bookId)
                    .setParameter("userId", userId)
                    .getSingleResult();

            return Optional.of(like);
        } catch (Exception e) {
            return Optional.of(new Like());
        }
    }
}

