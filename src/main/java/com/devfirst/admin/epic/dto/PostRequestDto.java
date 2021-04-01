package com.devfirst.admin.epic.dto;

import com.devfirst.admin.epic.domain.post.Post;

import com.devfirst.admin.epic.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor 
public class PostRequestDto {

	private long userId;
	private String title;
	private String content;
	private MultipartFile file;
	
	@Builder
	public PostRequestDto(String title, String content, long userId) {
		this.userId = userId;
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
