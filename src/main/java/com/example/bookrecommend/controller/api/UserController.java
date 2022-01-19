package com.example.bookrecommend.controller.api;

import com.example.bookrecommend.controller.dto.*;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

//    @PostMapping("/signup")
//    public CreateUserResponse signup(@Valid @RequestBody UserDto request) {
//
//        User signupUser = userService.signup(request);
//
//        log.info("signupUser: {}", signupUser);
//
//        //Entity->Dto
//        return new CreateUserResponse(signupUser);
//    }

    /** 회원저장 */
    @PostMapping("/signup")
    public ResponseDto signup(@Valid @RequestBody UserDto request,BindingResult result) {
        //데이터 검증시 오류가 있다면
        if(result.hasErrors()){
            //로직 중단
            return new ResponseDto(HttpStatus.BAD_REQUEST.value(), result.getFieldErrors());
        }

        //오류가 없다면 다음 로직들을 수행한다
        User signupUser = userService.signup(request);

        //Entity->Dto
        CreateUserResponse userResponse = new CreateUserResponse(signupUser);
        return new ResponseDto(HttpStatus.OK.value(), userResponse);
    }

//    @GetMapping("/profile/{userId}")
//    public SelectUserResponse findUser(@PathVariable Long userId) {
//        User findUser = userService.findById(userId);
//
//        //Entity->Dto
//        return new SelectUserResponse(findUser);
//    }

    /** 회원정보 조회 */
    @GetMapping("/profile/{userId}")
    public ResponseDto findUser(@PathVariable Long userId) {
        User findUser = userService.findById(userId);

        //Entity->Dto
        return new ResponseDto(HttpStatus.OK.value(),findUser);
    }

//    @PutMapping("/profile/{userId}")
//    public UpdateUserResponse updateUser(@PathVariable Long userId, @RequestBody UserDto request) {
//        log.info("id, request ; {} ", request, userId);
//
//        //회원 수정
//        userService.updateUser(userId, request);
//
//        //수정된 id로 findUser
//        User findUser = userService.findById(userId);
//
//        log.info("findUser : {} ", findUser);
//
//        //Entity->Dto
//        return new UpdateUserResponse(findUser);
//    }

    /** 회원수정 */
    @PutMapping("/profile/{userId}")
    public ResponseDto updateUser(@PathVariable Long userId, @RequestBody UserDto request) {
        log.info("id, request ; {} ", request, userId);

        //회원 수정
        userService.updateUser(userId, request);

        //수정된 id로 findUser
        User findUser = userService.findById(userId);

        log.info("findUser : {} ", findUser);

        //Entity->Dto
        return new ResponseDto(HttpStatus.OK.value(),findUser);
    }

    /** 회원 정보 삭제 */
    @DeleteMapping("/profile/delete/{userId}")
    public ResponseDto deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return new ResponseDto<>(HttpStatus.OK.value(), new UpdateUserResponse());
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