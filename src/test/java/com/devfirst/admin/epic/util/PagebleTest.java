package com.devfirst.admin.epic.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.PostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PagebleTest {
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void 페이징테스트() throws Exception {
		for(int i=1; i<=10; i++) {
			postRepository.save(Post.builder().title(i + "번: 제목").content(i + "번: 내용").build());
		}
		assertThat(postRepository.findAll().size()).isEqualTo(10);
		
		// WHEN
		PageRequest request = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
		Page<Post> result = postRepository.findAll(request);
		
		// THEN
		List<Post> posts = result.getContent();
		int totalPage = result.getTotalPages();
		boolean hasNext = result.hasNext();
		
		System.out.println(posts);
		System.out.println(totalPage);
		System.out.println(hasNext);
	}
}
