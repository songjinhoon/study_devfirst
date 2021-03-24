package com.devfirst.admin.epic.util;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BaseTimeEntityTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("BaseTimeEntity test")
    public void test() throws Exception {
        LocalDateTime testTime = LocalDateTime.of(2020, 6,4,0,0,0);
        postRepository.save(Post.builder().title("제목").content("시간").build());
        Post post = postRepository.findAll().get(0);

        assertThat(post.getCreatedDate()).isAfter(testTime);
        assertThat(post.getModifiedDate()).isAfter(testTime);
    }
}
