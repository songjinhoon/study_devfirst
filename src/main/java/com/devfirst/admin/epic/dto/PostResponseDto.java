package com.devfirst.admin.epic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostResponseDto {
	
	private Long id;
	private String title;
	private String content;
}
