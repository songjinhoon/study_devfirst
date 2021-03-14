package com.devfirst.admin.epic.util;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTimeEntityTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void baseTimeEntityTest() throws Exception {
        LocalDateTime testTime = LocalDateTime.of(2020, 6,4,0,0,0);
        postRepository.save(Post.builder().title("제목").content("시간").build());
        Post post = postRepository.findAll().get(0);

        assertThat(post.getCreatedDate()).isAfter(testTime);
        assertThat(post.getModifiedDate()).isAfter(testTime);
    }
}
