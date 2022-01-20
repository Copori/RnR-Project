package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.UserDto;
import com.example.bookrecommend.domain.Authority;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.repository.UserRepository;
import com.example.bookrecommend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // param으로 받은 user로 권한정보 생성, 가입은 USER로만
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        // 권한정보를 포함해 유저정보를 생성
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
//                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        // 유저정보 + 권한정보를 저장
        return userRepository.save(user);
    }

    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    // 현재 SecurityContext에 저장된 username만 갖고옴
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

    /** 회원정보 수정(username, email만) */
    @Transactional
    public void updateUser(Long id, UserDto request) {

        log.info("request : {}", request);

        // 요청 객체 추출
        Optional<User> findUser = userRepository.findById(id);

        // 추출한 객체에 수정 요청으로 들어온 값 세팅
        if (findUser.isPresent()) {
            User user = findUser.stream().findFirst().get();
            user.setId(id);
            user.setUsername(request.getUsername());
//            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
        }
    }


    /** 회원 단건 조회 */
    public User findById(Long id) {
        Optional<User> findUser = userRepository.findByIdAndActivatedTrue(id);

        User returnUser = findUser.stream().findFirst().orElseGet(() -> {
            // 없으면 빈 객체 리턴 TODO 에러로 처리하는 것으로 고민
            return new User();
        });

        return returnUser;
    }

    /** 회원 정보 삭제 */
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> findUser = userRepository.findById(id);

        //삭제할 유저가 존재할 경우
        if( findUser.isPresent() ) {
            User user = findUser.stream().findFirst().get();
            user.setActivated(false);
        }
    }
}