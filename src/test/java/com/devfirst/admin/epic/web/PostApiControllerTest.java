package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.PostResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.NoSuchElementException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // JPA WEB TEST
public class PostApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext context;

    @LocalServerPort
    private int port;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void cleanUp() {
        postRepository.deleteAll();
    }
    
    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("post save")
    public void test01() throws Exception {
        String title = "테스트제목";
        String content = "테스트내용";
        String url = "/post/api/save";

        mvc.perform(post(url)
            .param("title", title)
            .param("content", content))
            .andExpect(status().isOk());

        Post post = postRepository.findAll().stream().filter(data -> data.getTitle().equals(title)).findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser("USER")
    @DisplayName("post saveForJson")
    public void test011() throws Exception {
        String title = "테스트제목";
        String content = "테스트내용";
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .title(title)
                .content(content)
                .build();
        String url = "/post/api/saveForJson";

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(postRequestDto)))
                .andExpect(status().isOk());

        Post post = postRepository.findAll().stream().filter(data -> data.getTitle().equals(title)).findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    /* 여기부터 다시 수정들어가야함 */

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
