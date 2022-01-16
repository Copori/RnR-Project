//package com.example.bookrecommend.service;
//
//import com.example.bookrecommend.controller.dto.UserDTO;
//import com.example.bookrecommend.domain.User;
//import com.example.bookrecommend.repository.UserRepositoryTmp;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class UserServiceTmp {
//
//    private final UserRepositoryTmp userRepositoryTmp;
//
//    @Transactional
//    public UserDTO join(UserDTO param) {
//        // 중복체크
//        duplicateUserCheck(param);
//        // dto -> entity
//        User user = dtoToEntity(param);
//
//        // repo.save
//        userRepositoryTmp.save(user);
//
//        // entity -> dto
//        return entityToDto(user);
//    }
//
//    private void duplicateUserCheck(UserDTO param) {
//        Optional<User> findUser = userRepositoryTmp.findByName(param.getUsername());
//        if( findUser.isPresent() ) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
//
//    /** dto -> entity */
//    private User dtoToEntity(UserDTO userDTO) {
//        User user = new User();
//
//        user.setId(userDTO.getId());
//        user.setUsername(userDTO.getUsername());
//        //TODO hash값을 꺼내서 비교 후에 다시 평문으로 반환해야되는건가?
//        user.setPassword(userDTO.getPassword());
//        user.setEmail(userDTO.getEmail());
//
//        return user;
//    }
//
//    /** entity -> dto */
//    private UserDTO entityToDto(User user) {
//        UserDTO dto = new UserDTO();
//
//        dto.setId(user.getId());
//        dto.setUsername(user.getUsername());
//        dto.setPassword(user.getPassword());
//        dto.setEmail(user.getEmail());
//
//        return dto;
//    }
//
//
//}
