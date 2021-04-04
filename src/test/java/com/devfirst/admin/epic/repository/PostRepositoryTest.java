package com.devfirst.admin.epic.repository;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;

import com.devfirst.admin.epic.dto.PostResponseDto;
import com.devfirst.admin.epic.dto.QPostResponseDto;
import com.devfirst.admin.epic.dto.SearchDto;
import com.querydsl.core.QueryResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    
    Post post;

    @Test
    @DisplayName("findBySearchDto")
    public void test01() throws Exception {
        SearchDto searchDto = new SearchDto();
        PageRequest pageRequest = PageRequest.of(searchDto.getPage() - 1, 10, Sort.Direction.ASC, "id");
        Page<QPostResponseDto> results = postRepository.findBySearchDto(pageRequest, searchDto);
        List<QPostResponseDto> posts = results.getContent();
        System.out.println("::DEBUG::");
        System.out.println(posts.size());
        System.out.println(posts.get(0).getUserId());
        System.out.println(posts.get(0).getId());
    }
}
