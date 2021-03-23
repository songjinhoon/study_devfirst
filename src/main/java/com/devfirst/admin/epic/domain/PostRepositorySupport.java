package com.devfirst.admin.epic.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.devfirst.admin.epic.domain.QPost.post;

@Repository
public class PostRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public PostRepositorySupport(JPAQueryFactory queryFactory) {
        super(Post.class);
        this.queryFactory = queryFactory;
    }

    public List<Post> findByTitle(String title) {
        return queryFactory.selectFrom(post)
                .where(post.title.eq(title))
                .fetch();
    }
}
