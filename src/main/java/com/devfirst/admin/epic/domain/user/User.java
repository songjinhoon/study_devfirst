package com.devfirst.admin.epic.domain.user;

import com.devfirst.admin.epic.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "M_USER")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_USER_ID")
    private Long id;

    @Column(name = "M_USER_NAME")
    private String name;

    @Column(name = "M_USER_EMAIL")
    private String email;

    @Column(name = "M_USER_PICTURE")
    private String picture;
/*
    @Enumerated(EnumType.STRING)
    @Column(name = "M_USER_ROLE")
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }*/

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        // return이 필요한가?
        return this;
    }
}