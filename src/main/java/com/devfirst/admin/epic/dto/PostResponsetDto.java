package com.devfirst.admin.epic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponsetDto {
	
	private Long id;
	private String title;
	private String content;
}
