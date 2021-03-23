package com.devfirst.admin.epic.util;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.mapper.PostMapper;
import com.devfirst.admin.epic.dto.PostResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapstructTest {

    @Test
    @DisplayName("Mapstruct test")
    public void test() throws Exception {
        Post post = Post.builder().title("제목").content("내용").build();
        PostResponseDto postResponseDto = PostMapper.INSTANCE.postToPostResponseDto(post);
        System.out.println("::DEBUG:: " + post.getTitle() + " = " + postResponseDto.getTitle());
        assertThat(post.getTitle()).isEqualTo(postResponseDto.getTitle());
    }
}
