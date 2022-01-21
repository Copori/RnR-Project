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
//@CrossOrigin(origins = "http://localhost:3000")
public class LikeApiController {

    private final LikeService likeService;

    /** 선택도서 */
    @GetMapping("/books/like/{userId}")
    public ResponseDto findBookList(@PathVariable Long userId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // 책 목록 조회
        List<BookListDto> bookListDtos = likeService.selectBookList(username);

        return new ResponseDto(HttpStatus.OK.value(), bookListDtos);
    }

    /** 좋아요 저장 */
    @PutMapping("/like/{bookId}")
    public ResponseEntity likeSaveAndCancel(@PathVariable Long bookId) {

        //시큐리티 컨텍스트에서 찾아옴
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 현재 로그인한 username
        String username = auth.getName();
        Object principal = auth.getPrincipal();

        log.info("현재 로그인한 user_name : {}", username);
        log.info("현재 로그인한 principal : {}", principal);

        likeService.likeSaveAndCancel(bookId, username);

        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.value());
    }

}
