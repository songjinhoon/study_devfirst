package com.devfirst.admin.epic.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "M_POST")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "M_POST_ID")
    private Long id;

    @Column(name = "M_POST_TITLE")
    private String title;

    @Column(name = "M_POST_CONTENT")
    private String content;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}