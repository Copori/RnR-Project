//package com.example.bookrecommend.repository;
//
//import com.example.bookrecommend.domain.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@RequiredArgsConstructor
//public class UserRepositoryTmp {
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    public Optional<User> findByName(String username) {
//        List<User> results = em.createQuery("select u from User u where u.username = :username", User.class)
//                .setParameter("username", username)
//                .getResultList();
//
//        return results.stream().findFirst();
//    }
//
//    public void save(User user) {
//        em.persist(user);
//    }
//}
