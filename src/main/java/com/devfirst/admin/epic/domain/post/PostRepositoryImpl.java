package com.devfirst.admin.epic.domain.post;

import com.devfirst.admin.epic.domain.user.QUser;
import com.devfirst.admin.epic.dto.QPostResponseDto;
import com.devfirst.admin.epic.dto.SearchDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.devfirst.admin.epic.domain.post.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> findByTitle(String title) {
        return queryFactory.selectFrom(post)
                .where(post.title.eq(title))
                .fetch();
    }

    @Override
    public Page<QPostResponseDto> findBySearchDto(Pageable pageable, SearchDto searchDto) {
        QueryResults<QPostResponseDto> result = queryFactory.select(Projections.bean(QPostResponseDto.class,
                post.id,
                post.title,
                post.content,
                post.user.id.as("userId")))
                .from(post)
                .where(eqTitle(searchDto.getTitle()), eqContent(searchDto.getContent()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return post.title.contains(title);
    }
    private BooleanExpression eqContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        return post.content.contains(content);
    }

    private void testing(QUser quser) {

    }
}