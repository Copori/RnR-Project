package com.example.bookrecommend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class MyReviewSummaryDto {
    //좋아요 총개수
    private int likeCnt;
    //책 데이터 시작
    private List<ReviewDto> reviewDtoList;
    //총 데이터 건수
    private int total;
    //평점 평균
    private double reviewAvg;
}
