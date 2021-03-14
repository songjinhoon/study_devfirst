package com.devfirst.admin.epic.dto;

import com.devfirst.admin.epic.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
	
	private Long id;
	private String title;
	private String content;
}
