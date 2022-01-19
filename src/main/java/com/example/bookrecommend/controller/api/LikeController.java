package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.BookListDto;
import com.example.bookrecommend.controller.dto.ResponseDto;
import com.example.bookrecommend.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    private final LikeService likeService;

    /** 사용자가 좋아요 한 책 목록 */
    @GetMapping("/books/{userId}")
    public ResponseDto findBookList(@PathVariable Long userId) {

        /** 방안 1: 현재 접속한 username으로 찾음 => key값이 아님 */
        /** 방안 2: userId로 조회를 해옴 => client에 login을 하면 userId를 전달*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 책 목록 조회
        List<BookListDto> bookListDtos = likeService.selectBookList(username);

        return new ResponseDto(HttpStatus.OK.value(), bookListDtos);
    }

    // 책 상세 페이지에서 처리
    /** 책의 전체 좋아요 개수 */

    /** 좋아요 저장 */
    @PutMapping("/like/{bookId}")
    public ResponseEntity saveLikes(@PathVariable Long bookId) {

        //시큐리티 컨텍스트에서 찾아옴
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 현재 로그인한 username
        String username = auth.getName();
        Object principal = auth.getPrincipal();

        log.info("현재 로그인한 user_name : {}", username);
        log.info("현재 로그인한 principal : {}", principal);

        likeService.saveLikes(bookId, username);

        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.value());
    }

    /** 좋아요 취소 */

}
