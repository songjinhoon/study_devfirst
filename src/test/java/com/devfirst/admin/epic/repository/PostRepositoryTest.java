package com.devfirst.admin.epic.repository;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.PostRepository;
import org.junit.After;
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

    @After
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 포스트저장불러오기() throws Exception {
        postRepository.save(Post.builder()
            .title("제목").content("내용").build());
        Post post = postRepository.findAll().get(0);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("제목");
    }
}
