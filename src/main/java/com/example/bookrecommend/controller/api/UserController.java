package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.UpdateUserResponse;
import com.example.bookrecommend.controller.dto.UserDto;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    /** 
     * 회원저장
     * TODO response를 User entity가 아닌 따로 분리예정
     */
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    /** 회원수정 */
    @PutMapping("/user/{id}")
    public UpdateUserResponse update(@PathVariable Long id,
                                                     @RequestBody UserDto request) {
        log.info("id, request ; {} ", request, id);

        userService.update(id, request);
        User findUser = userService.findById(id);

        log.info("findUser : {} ", findUser);

        return new UpdateUserResponse(findUser.getUserId()
                ,findUser.getUsername()
                ,findUser.getPassword()
                ,findUser.getNickname()
                ,findUser.getEmail());
    }

    //Token만
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')") //두 권한 모두 허용
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')") //Admin만
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}