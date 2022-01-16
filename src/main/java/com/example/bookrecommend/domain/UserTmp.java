//package com.example.bookrecommend.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//
//@Entity
//@Getter @Setter
//public class UserTmp {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private long id;
//
//    //null 불가 아이디는 30자 불가
//    @Column(nullable = false, length = 30)
//    private String username;
//
//    //해시로 변경해서 암호화하기에 길이는 넉넉하게
//    @Column(nullable = false, length = 200)
//    private String password;
//
//    @Column(nullable = false, length = 50)
//    private String email;
//
//    @ColumnDefault("'user'")
//    private String role;
//
//    @CreationTimestamp //시간 자동 입력(insert시점)
//    private Timestamp createdAt;
//
//    @CreationTimestamp
//    private Timestamp updatedAt;
//}