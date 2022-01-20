package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.BookListDto;
import com.example.bookrecommend.domain.Like;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.repository.LikeRepository;
import com.example.bookrecommend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    /** 사용자의 좋아요 책 목록 */
    public List<BookListDto> selectBookList(String username) {

        // 사용자의 책 목록 추출
        List<Long> findBookId = likeRepository.findLikeAllByUsername(username);

        List<BookListDto> result = findBookId.stream()
                .map(b -> new BookListDto(b))
                .collect(Collectors.toList());

        return result;
    }

    /** 책의 좋아요 총 개수*/
    public Integer selectBookCnt(Long bookId) {

        // 리스트 추출
        List<Like> likeCnt = likeRepository.findLikeCnt(bookId);

        // 사이즈 계산
        Integer bookCnt = likeCnt.size();

        return bookCnt;
    }

    /** 좋아요 저장 및 취소*/
    @Transactional
    public void LikeSaveAndCancel(Long bookId, String username) {

        // 현재 접속한 아이디로 user객체를 찾아와
        User findUser = userRepository.findByUsername(username).orElseGet(() -> {
            return new User();
        });

        // 1. 현재 데이터 존재 유무 체크
        Optional<Like> likeOptional = likeRepository.findByBookIdAndUserId(bookId, findUser.getId());

        // 데이터 존재할 경우
        likeOptional.ifPresent(like -> {
            if (like.isActivated() == true) {
                // 좋아요 -> 좋아요 취소
                like.setActivated(false);
            } else if (like.isActivated() == false) {
                if (like.getId() != null) {
                    // 좋아요 취소 -> 좋아요
                    like.setActivated(true);
                } else {
                    // 데이터 없으면
                    Like newLike = new Like();
                    newLike.setBookId(bookId);
                    newLike.setUser(findUser);
                    newLike.setActivated(true);

                    likeRepository.LikeSaveAndCancel(newLike);
                }
            }
        });

    }
}
