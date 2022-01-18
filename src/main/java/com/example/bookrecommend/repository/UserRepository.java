package com.example.bookrecommend.repository;

import com.example.bookrecommend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 쿼리수행을 Eager조회로 authorities정보도 같이 갖고옴
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    // 삭제되지 않은 user만 조회
    Optional<User> findByIdAndActivatedTrue(Long id);

    Optional<User> findByUsername(String username);
}
