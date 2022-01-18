package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.BookListDto;
import com.example.bookrecommend.domain.Like;
import com.example.bookrecommend.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;

    /** 사용자의 좋아요 책 목록 */
    public List<BookListDto> selectBookList(Long userId) {

        // 사용자의 책 목록 추출
        List<Like> findBook = likeRepository.findLikeAllByUserId(userId);

        List<BookListDto> result = findBook.stream()
                .map(b -> new BookListDto(b.getBookId()))
                .collect(Collectors.toList());

        return result;
    }

    /** 책의 좋아요 개수 */
    public Integer selectBookCnt(Long bookId) {
        
        // 리스트 추출
        List<Like> likeCnt = likeRepository.findLikeCnt(bookId);
        
        // 사이즈 계산
        Integer bookCnt = likeCnt.size();

        return bookCnt;
    } 
}
