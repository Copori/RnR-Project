package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.*;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    /** 회원저장 */
    @PostMapping("/signup")
    public CreateUserResponse signup(@Valid @RequestBody UserDto request) {
        User signupUser = userService.signup(request);

        //Entity->Dto
        return new CreateUserResponse(signupUser);
    }

    /** 회원저장 */
    @PostMapping("/signup")
    public ResponseDto signup2(@Valid @RequestBody UserDto request) {
        User signupUser = userService.signup(request);

        //Entity->Dto
        CreateUserResponse u = new CreateUserResponse(signupUser);
        return new ResponseDto(HttpStatus.OK.value(), u);
    }

    /** 회원정보 조회 */
    @GetMapping("/profile/{userId}")
    public SelectUserResponse findUser(@PathVariable Long userId) {
        User findUser = userService.findById(userId);

        //Entity->Dto
        return new SelectUserResponse(findUser);
    }

    /** 회원수정 */
    @PutMapping("/profile/{userId}")
    public UpdateUserResponse update(@PathVariable Long userId, @RequestBody UserDto request) {
        log.info("id, request ; {} ", request, userId);

        //회원 수정
        userService.update(userId, request);

        //수정된 id로 findUser
        User findUser = userService.findById(userId);

        log.info("findUser : {} ", findUser);

        //Entity->Dto
        return new UpdateUserResponse(findUser);
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