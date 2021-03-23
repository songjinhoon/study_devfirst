package com.devfirst.admin.epic.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.PostRepository;

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
