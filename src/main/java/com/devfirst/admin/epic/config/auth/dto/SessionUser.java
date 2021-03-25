package com.devfirst.admin.epic.config.auth.dto;

import com.devfirst.admin.epic.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
/*
* User class를 session에 저장하지 않고 별도의 SessionUser를 만든 이유는, User class가 Entity이기 때문이다.
* 그렇기에, 별도의 class를 만들어서 직렬화(Serializable)를 구현한다.
* Entity는 언제라도 다른 Entity와 관계가 형성될 수 있고, 관계가 형성되면 직렬화 대상에 다른 Entity까지 포함되니 성능이슈 및 부수 효과가 발생할 수 있다.
* */