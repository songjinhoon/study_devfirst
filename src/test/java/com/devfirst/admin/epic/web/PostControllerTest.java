package com.devfirst.admin.epic.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void index_페이지() throws Exception {
        mvc.perform(get("/post/list")) // contextpath 제외해야함.
                .andExpect(status().isOk());
    }
}
