package com.example.bookrecommend.controller.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotEmpty(message = "아이디는 필수 입니다.")
    @Size(min = 3, max = 50)
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    @Size(min = 3, max = 100)
    private String password;

    @NotEmpty(message = "이메일은 필수 입니다.")
    @Size(min = 3, max = 50)
    private String email;
}