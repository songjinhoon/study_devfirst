package com.devfirst.admin.epic.domain.post;

import com.devfirst.admin.epic.domain.BaseTimeEntity;
import com.devfirst.admin.epic.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "M_POST")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_POST_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "M_USER_ID")
    private User user;

    @Column(name = "M_POST_TITLE")
    private String title;

    @Column(name = "M_POST_CONTENT")
    private String content;

    @Builder
    public Post(String title, String content, User user) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}