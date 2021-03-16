package com.devfirst.admin.epic.domain.mapper;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.dto.PostResponseDto;

//MapStruct 는 Target 객체에 @Builder 어노테이션이 달려있다면 Builder 메소드를 우선 사용
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface PostMapper {
	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
	
	PostResponseDto postToPostResponseDto(Post post);
	List<PostResponseDto> postsToPostResponseDtos(List<Post> posts);
}