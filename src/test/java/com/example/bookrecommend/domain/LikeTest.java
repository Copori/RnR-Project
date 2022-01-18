package com.example.bookrecommend.domain;

import com.example.bookrecommend.repository.LikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class LikeTest {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 객체저장_테스트() {
        Like like = Like
                .builder()
                .bookId(1L)
                .build();

        em.persist(like);
        System.out.println("getId는?" + like.getId());

    }

}