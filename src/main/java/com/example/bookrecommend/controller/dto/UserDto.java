package com.example.bookrecommend.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotEmpty(message = "아이디를 입력하세요 합니다.")
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @Size(min = 3, max = 100)
    private String password;

    @Size(min = 3, max = 50)
    private String email;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}