package com.devfirst.admin.epic.util;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.mapper.PostMapper;
import com.devfirst.admin.epic.dto.PostResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapstructTest {

    @Test
    public void mapstructTest() throws Exception {
        Post post = Post.builder().title("제목").content("내용").build();
        PostResponseDto postResponseDto = PostMapper.INSTANCE.postToPostResponseDto(post);
        System.out.println("::DEBUG:: " + post.getTitle() + " = " + postResponseDto.getTitle());
        assertThat(post.getTitle()).isEqualTo(postResponseDto.getTitle());
    }
}
