package com.devfirst.admin.epic.dto;

import com.devfirst.admin.epic.domain.Post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor 
public class PostRequestDto {
	
	private String title;
	private String content;
	
	@Builder
	public PostRequestDto(String title, String content) {
		this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}