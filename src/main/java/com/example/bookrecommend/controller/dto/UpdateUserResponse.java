package com.example.bookrecommend.controller.dto;

import com.example.bookrecommend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponse {

    private Long id;
    private String username;
    private String password;
//    private String nickname;
    private String email;

    public UpdateUserResponse(User user) {
        username = user.getUsername();
        password = user.getPassword();
//        nickname = user.getNickname();
        email = user.getEmail();
    }
}
