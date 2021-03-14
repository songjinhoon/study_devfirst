package com.devfirst.admin.epic.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void index_페이지() throws Exception {
        String body = this.restTemplate.getForObject("/post/list", String.class);
        
        assertThat(body).contains("하위하위");
    }
}
