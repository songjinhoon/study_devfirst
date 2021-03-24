package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.PostResponseDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // JPA WEB TEST
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @AfterAll
    public void clean() throws Exception {
        postRepository.deleteAll();
    }
    
    //한방메소드 만들자
    
    @Test
    @DisplayName("post save")
    public void test01() throws Exception {
        PostRequestDto postRequestDto = PostRequestDto.builder().content("내용").title("제목").build();
        String url = "http://localhost:" + port + "/dev/post/api/save";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postRequestDto, Long.class);
        Post post = postRepository.findAll().get(0);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(post.getTitle()).isEqualTo("제목");
    }

    @Test
    @DisplayName("post get")
    public void test02() throws Exception {
        PostRequestDto postRequestDto = PostRequestDto.builder().title("제목").content("내용").build();
        Post post = postRequestDto.toEntity();
        postRepository.save(post);
        String url = "http://localhost:" + port + "/dev/post/api/find/1";

        HttpEntity<PostRequestDto> requestEntity = new HttpEntity<>(postRequestDto);
        ResponseEntity<PostResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PostResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("post update")
    public void test03() throws Exception {
        Post post = postRepository.save(Post.builder().title("제목1").content("내용1").build());
        Long id = post.getId();
        String url = "http://localhost:" + port + "/dev/post/api/update/" + id;

        PostRequestDto postRequestDto =  PostRequestDto.builder().title("제목2").content("내용2").build();
        ResponseEntity<PostResponseDto> responseEntity = restTemplate.postForEntity(url, postRequestDto, PostResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Post updatedPost = postRepository.findById(id).orElse(null);
        assertThat(updatedPost.getTitle()).isEqualTo("제목2");
        assertThat(updatedPost.getContent()).isEqualTo("내용2");
    }
}
