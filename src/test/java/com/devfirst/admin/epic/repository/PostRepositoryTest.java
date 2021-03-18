package com.devfirst.admin.epic.repository;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.PostRepository;
import com.devfirst.admin.epic.domain.mapper.PostMapper;
import com.devfirst.admin.epic.dto.PostResponseDto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    
    Post post;
    
    @Before
    public void init() {
    	
    }

    @After
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 포스트저장불러오기() throws Exception {
        postRepository.save(post);
        Post post = postRepository.findAll().get(0);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("제목");
    }
    
    @Test
    public void Mapstruct_테스트() throws Exception {
    	Post post = postRepository.findById(1l).orElse(null);
    	PostResponseDto postResponsetDto = PostMapper.INSTANCE.postToPostResponseDto(post);
    	assertThat(postResponsetDto.getTitle()).isEqualTo(post.getTitle());
    }
}
