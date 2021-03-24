package com.devfirst.admin.epic.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import com.devfirst.admin.epic.domain.post.PostRepositorySupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuerydslTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRepositorySupport postRepositorySupport;

    @Test
    @DisplayName("Querydsl test")
    public void test01() {
        String title = "제목100";
        String content = "내용100";
        postRepository.save(Post.builder().title(title).content(content).build());

        //List<Post> posts = postRepositorySupport.findByTitle(title);
        List<Post> posts = postRepository.findByTitle(title);

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
    }
}