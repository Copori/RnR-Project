package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Like, Long> {

    // 좋아요 총 개수

    // 댓글 목록 조회
    // findAllById
}

