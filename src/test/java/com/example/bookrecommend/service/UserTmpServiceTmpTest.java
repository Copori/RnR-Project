//package com.example.bookrecommend.service;
//
//import com.example.bookrecommend.domain.UserTmp;
//import com.example.bookrecommend.repository.UserRepositoryTmp;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Transactional
//public class UserTmpServiceTmpTest {
//
//    @Autowired
//    private UserRepositoryTmp userRepositoryTmp;
//
//    @Test
//    public void 회원_가입() {
//        UserTmp userTmp = new UserTmp();
//        userTmp.setUsername("jaeimn");
//        userTmp.setEmail("jasemin@naver.com");
//        userTmp.setPassword("1234");
//
//        userRepositoryTmp.save(userTmp);
//    }
//}