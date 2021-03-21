package com.devfirst.admin.epic.service;

import com.devfirst.admin.epic.domain.Post;
import com.devfirst.admin.epic.domain.PostRepository;
import com.devfirst.admin.epic.domain.mapper.PostMapper;
import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.PostResponseDto;
import com.google.common.collect.ImmutableMap;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostRequestDto postRequestDto) {
        return postRepository.save(postRequestDto.toEntity()).getId();
    }

    public PostResponseDto findById(final Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return PostMapper.INSTANCE.postToPostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(PostRequestDto postRequestDto, final Long id) {
        Post post = postRepository.findById(id).orElse(null);
        post.update(postRequestDto.getTitle(), postRequestDto.getContent());
        return PostMapper.INSTANCE.postToPostResponseDto(post);
    }
    
    public List<PostResponseDto> findBySearchParameter(final Pageable pageable) {
    	Page<Post> result = postRepository.findAll(pageable);
    	List<Post> posts = result.getContent();
    	List<PostResponseDto> postsResponseDtos = PostMapper.INSTANCE.postsToPostResponseDtos(posts); 
    	
    	return postsResponseDtos;
    }
    
    public ImmutableMap<String, Object> findAll(final Pageable pageable) {
    	Page<Post> result = postRepository.findAll(pageable);
    	
    	ImmutableMap<String, Object> pageAndpostResponseDtos = ImmutableMap.<String, Object>builder()
    			.put("posts", PostMapper.INSTANCE.postsToPostResponseDtos(result.getContent()))
    			.put("pageInfo", result).build();

    	return pageAndpostResponseDtos;
    }
}