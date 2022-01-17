package com.example.bookrecommend.controller.dto;

import com.example.bookrecommend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {

    private String username;
    private String nickname;
    private String email;

    public CreateUserResponse(User user) {
        username = user.getUsername();
        nickname = user.getNickname();
        email = user.getEmail();
    }
}
