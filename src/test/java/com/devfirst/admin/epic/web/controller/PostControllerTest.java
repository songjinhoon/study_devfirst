package com.devfirst.admin.epic.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PostControllerTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("index")
    public void test01() throws Exception {
        int page = 1;
        String title = "테스트제목";
        String content = "테스트내용";
        String url = "/post/list";
        String expectViewName = "/post/index";

        mvc.perform(get(url)
                .param("param", String.valueOf(page))
                .param("title", title)
                .param("content", content))
                .andExpect(status().isOk())
                /*.andExpect(content().string("Hello World")) // response body에 문자열이 포함되있니*/
                //.andExpect(model().attribute("posts", IsCollectionWithSize.hasSize(10)))
                .andExpect(view().name(expectViewName))
                .andExpect(model().attributeExists("posts"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("postWrite")
    public void test02() throws Exception {
        String url = "/post/write";
        String expectViewName = "/post/postWrite";

        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name(expectViewName));
    }

    /*@Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("template test")
    public void test() throws Exception {
        String body = this.restTemplate.getForObject("/post/list", String.class);
        
        assertThat(body).contains("게시글 작성");
    }*/
}
