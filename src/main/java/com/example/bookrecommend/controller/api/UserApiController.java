//package com.example.bookrecommend.controller.api;
//
//import com.example.bookrecommend.controller.dto.UserDTO;
//import com.example.bookrecommend.service.UserServiceTmp;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class UserApiController {
//
//    private final UserServiceTmp userServicetmp;
//
//    /** 회원가입 */
//    @PostMapping("/api/auth/joinProc")
//    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO) {
//        log.info("MemberApiController: saveUser(회원가입) 호출");
//
//        UserDTO joinUser = userServicetmp.join(userDTO);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(joinUser);
//    }
//
//}
