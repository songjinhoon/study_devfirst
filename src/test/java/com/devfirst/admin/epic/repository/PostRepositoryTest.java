package com.devfirst.admin.epic.repository;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    
    Post post;
    
    @BeforeAll
    public void init() {
    	
    }

    @AfterAll
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("post save and get")
    public void test01() throws Exception {
        postRepository.save(post);
        Post post = postRepository.findAll().get(0);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("제목");
    }
}
