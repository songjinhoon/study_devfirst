package com.devfirst.admin.epic.domain.post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findByTitle(String title);
}
