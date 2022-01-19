package com.example.bookrecommend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @JsonIgnore // 서버에서 JSon응답을 생성할때 해당 필드는 ignore
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    private int reviewScore;

    private String reviewContent;

    @JsonIgnore
    private boolean activated;

    @CreationTimestamp //시간 자동 입력(insert시점)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
