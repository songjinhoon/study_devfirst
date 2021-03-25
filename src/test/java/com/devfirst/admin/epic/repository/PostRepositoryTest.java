package com.devfirst.admin.epic.repository;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;

import com.devfirst.admin.epic.dto.PostResponseDto;
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
    @DisplayName("post save and get")
    public void test01() throws Exception {
        postRepository.save(post);
        Post post = postRepository.findAll().get(0);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("제목");
    }

    @Test
    @DisplayName("post search test")
    public void test02() throws Exception {
        SearchDto searchDto = new SearchDto();
        searchDto.setTitle("제목50");
        searchDto.setContent("내용");
        PageRequest pageRequest = PageRequest.of(searchDto.getPage() - 1, 4, Sort.Direction.ASC, "id");

        Page<PostResponseDto> result = postRepository.findBySearchDto(pageRequest, searchDto);

        System.out.println("::DEBUG::");
        System.out.println(result.getContent());
    }
}
