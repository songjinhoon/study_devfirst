package com.devfirst.admin.epic.dto;

import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import com.devfirst.admin.epic.domain.user.User;
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
	private SessionUser sessionUser;
}
