package com.devfirst.admin.epic.domain.post;

import com.devfirst.admin.epic.dto.PostResponseDto;
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
import static com.querydsl.jpa.JPAExpressions.select;

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
    public Page<PostResponseDto> findBySearchDto(Pageable pageable, SearchDto searchDto) {
        QueryResults<PostResponseDto> result = queryFactory.select(Projections.bean(PostResponseDto.class,
                post.id,
                post.title,
                post.content))
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
}