package com.example.bookrecommend.controller.dto;

import com.example.bookrecommend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {

    private Long id;
    private User userId;
    private Long bookId;
}
