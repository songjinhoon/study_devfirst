package com.devfirst.admin.epic.domain;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findByTitle(String title);
}
