package com.devfirst.admin.epic.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devfirst.admin.epic.domain.post.PostRepository;

@SpringBootTest
public class PagebleTest {
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	@DisplayName("Page test")
	public void test() throws Exception {
		//
	}
}
