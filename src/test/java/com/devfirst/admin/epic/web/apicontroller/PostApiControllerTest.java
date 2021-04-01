package com.devfirst.admin.epic.web.apicontroller;

import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import com.devfirst.admin.epic.domain.user.Role;
import com.devfirst.admin.epic.domain.user.User;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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

    /*@AfterEach
    public void cleanUp() {
        postRepository.deleteAll();
    }*/
    
    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("save")
    public void test01() throws Exception {
        long userId = 1;
        String title = "테스트제목";
        String content = "테스트내용";
        String url = "/post/api/save";

        mvc.perform(post(url)
            .param("title", title)
            .param("content", content)
            .param("userId", String.valueOf(userId)))
                .andExpect(status().isOk());

        Post post = postRepository.findAll().stream().filter(data -> data.getTitle().equals(title)).findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        //잠시대기
        /*System.out.println("::DEUBG:: " + post.getUser().getId());*/
        /*System.out.println("::DEUBG:: " + post.getUser().getName());*/
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("PostApiController.saveJson()")
    public void test02() throws Exception {
        String title = "테스트제목";
        String content = "테스트내용";
        String url = "/post/api/saveForJson";
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(postRequestDto)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());;

        Post post = postRepository.findAll().stream().filter(data -> data.getTitle().equals(title)).findFirst().orElseThrow(NoSuchElementException::new);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("find")
    public void test03() throws Exception {
        long id = 1;
        String url = "/post/api/find";
        Post post = postRepository.findAll().stream().filter(data -> data.getId() == 1).findFirst().orElseThrow(NoSuchElementException::new);

        mvc.perform(get(url + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value(post.getTitle()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("update")
    public void test04() throws Exception {
        String title = "제목업데이트";
        String content = "타이틀업데이트";
        long id = 1;
        String url = "/post/api/update/" + id;
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(postRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("title").value(title))
                .andDo(MockMvcResultHandlers.print());
    }
/*    @Test
    @DisplayName("post update")
    public void test04() throws Exception {
        Post post = postRepository.save(Post.builder().title("제목1").content("내용1").build());
        Long id = post.getId();
        String url = "http://localhost:" + port + "/dev/post/api/update/" + id;

        PostRequestDto postRequestDto =  PostRequestDto.builder().title("제목2").content("내용2").build();
        ResponseEntity<PostResponseDto> responseEntity = restTemplate.postForEntity(url, postRequestDto, PostResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Post updatedPost = postRepository.findById(id).orElse(null);
        assertThat(updatedPost.getTitle()).isEqualTo("제목2");
        assertThat(updatedPost.getContent()).isEqualTo("내용2");
    }*/
}
