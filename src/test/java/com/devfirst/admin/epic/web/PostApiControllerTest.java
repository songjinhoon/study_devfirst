package com.devfirst.admin.epic.web;

import com.devfirst.admin.epic.config.auth.SecurityConfig;
import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.PostResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ContextConfiguration(classes = SecurityConfig.class)
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

    //한방메소드 만들자
    
    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("post save")
    public void test01() throws Exception {
        String title = "테스트제목";
        String content = "테스트내용";
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .content(content)
                .title(title)
                .build();
        String url = "http://localhost:" + port + "/dev/post/api/save";

        System.out.println("@@" + url);

        mvc.perform(get("/post/api/save")
            .param("title", title)
            .param("content", content))
            .andExpect(status().isOk());

        /*Post post = postRepository.findAll().stream().filter(data -> data.getTitle().equals(title)).findFirst().orElse(null);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);*/

/*        mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(new ObjectMapper().writeValueAsString(postRequestDto)))
            .andExpect(status().isOk());*/

/*        Post post = postRepository.findByTitle(title).get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);*/
        /*ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, postRequestDto, Long.class);
        Post post = postRepository.findAll().stream().filter(data -> data.getTitle().equals(title)).findFirst().orElse(null);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(post.getContent()).isEqualTo(content);*/
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
