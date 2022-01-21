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
public class UserApiController {
    private final UserService userService;

    /** 회원가입 */
    @PostMapping("/signup")
    public ResponseDto signup(@Valid @RequestBody UserDto request,BindingResult result) {
        //데이터 검증시 오류가 있다면
        if(result.hasErrors()){
            //로직 중단
            return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getFieldErrors());
        }

        //오류가 없다면 다음 로직들을 수행한다
        User signupUser = userService.signup(request);

        //Entity->Dto
        CreateUserResponse userResponse = new CreateUserResponse(signupUser);
        return new ResponseDto(HttpStatus.OK.value(), userResponse);
    }

    /** 회원정보 조회 */
    @GetMapping("/profile/{userId}")
    public ResponseDto findUser(@PathVariable Long userId) {
        User findUser = userService.findById(userId);

        //Entity->Dto
        return new ResponseDto(HttpStatus.OK.value(),findUser);
    }

    /** 회원정보 수정(username, email만) */
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
    @PatchMapping("/profile/{userId}")
    public ResponseDto updateUser(@PathVariable Long userId, @RequestBody UserDto request) {
        log.info("id, request ; {} ", request, userId);

        //회원 수정
        userService.updateUser(userId, request);

        //수정된 id로 findUser
        User findUser = userService.findById(userId);

        log.info("findUser : {} ", findUser);

        //Entity->Dto
        return new ResponseDto(HttpStatus.OK.value(), findUser);
    }


    /** 비밀번호 수정
     /**
     * 1.Patch를 사용해 회원 정보 일부 수정을 하려 했으나 비밀번호를 안 적을 경우 수정이 안됨
     * 2. 현재 SpringDataJPA로 처리할 여유가 없기에
     * 3. TODO 회원정보수정을 usename,email수정기능과 비밀번호 수정기능으로 분리할 예정
     *
     */
//    @PatchMapping("/profile/{userId}")
//    public ResponseDto updateUserPassword(@PathVariable Long userId, @RequestBody UserDto request) {
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
//        return new ResponseDto(HttpStatus.OK.value(),findUser);
//    }

    /** 회원 정보 삭제(탈퇴) */
    @PatchMapping("/profile/delete/{userId}")
    public ResponseDto deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return new ResponseDto<>(HttpStatus.OK.value());
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