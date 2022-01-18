package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.BookListDto;
import com.example.bookrecommend.controller.dto.ResponseDto;
import com.example.bookrecommend.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        // 책 목록 조회
        List<BookListDto> bookListDtos = likeService.selectBookList(userId);

        return new ResponseDto(HttpStatus.OK.value(), bookListDtos);
    }

    // 책 상세 페이지에서 처리
    /** 책의 전체 좋아요 개수 */


    /** 좋아요 저장 */

    /** 좋아요 취소 */

    /**  */

}
