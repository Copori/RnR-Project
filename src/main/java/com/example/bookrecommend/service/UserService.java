package com.example.bookrecommend.service;

import com.example.bookrecommend.controller.dto.UserDto;
import com.example.bookrecommend.domain.Authority;
import com.example.bookrecommend.domain.User;
import com.example.bookrecommend.repository.UserRepository;
import com.example.bookrecommend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // param으로 받은 user로 권한정보 생성
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        // 권한정보를 포함해 유저정보를 생성
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        // 유저정보 + 권한정보를 저장
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    // 현재 SecurityContext에 저장된 username만 갖고옴
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

    /**
     * 회원정보 수정
     */
    @Transactional
    public void update(Long id, UserDto request) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            User user = findUser.stream().findFirst().get();
            user.setUserId(id);
            user.setUsername(request.getUsername());
            //TODO 시큐리티에서 회원정보 어떻게 하는지
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
        }
    }

    public User findById(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        return findUser.stream().findFirst().get();
    }
}