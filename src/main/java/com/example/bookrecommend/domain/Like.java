package com.example.bookrecommend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Table(name = "likes")
@DynamicInsert // activated컬럼이 default 'N'으로 유지되도록
public class Like {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long bookId;

    @JsonIgnore
    private boolean activated;

    @CreationTimestamp //시간 자동 입력(insert시점)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    /** 연관관계 메서드 */
    public void setUser(User user) {
        //User에 param의 user
        this.user = user;
        //User의 likes에 현재Like객체 자신을 세팅
        user.getLikes().add(this);
    }

    /** 생성 메서드 */
    public static Like createLike(User user, Long bookId) {
        Like like = new Like();
        like.setUser(user);
        like.setBookId(bookId);
        like.setActivated(false);
        return like;
    }


}
