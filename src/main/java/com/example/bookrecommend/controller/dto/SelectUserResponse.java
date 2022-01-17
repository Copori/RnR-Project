package com.example.bookrecommend.controller.dto;

import com.example.bookrecommend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectUserResponse {

    private String username;
    private String nickname;
    private String email;

    public SelectUserResponse(User user) {
        username = user.getUsername();
        nickname = user.getNickname();
        email = user.getEmail();
    }
}
