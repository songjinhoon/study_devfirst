package com.devfirst.admin.epic.service;

import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import com.devfirst.admin.epic.domain.mapper.UserMapper;
import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import com.devfirst.admin.epic.domain.mapper.PostMapper;
import com.devfirst.admin.epic.domain.user.User;
import com.devfirst.admin.epic.domain.user.UserRepository;
import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.PostResponseDto;
import com.devfirst.admin.epic.dto.QPostResponseDto;
import com.devfirst.admin.epic.dto.SearchDto;
import com.google.common.collect.ImmutableMap;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto save(PostRequestDto postRequestDto) throws IOException {
        Optional<MultipartFile> fileCheck = Optional.ofNullable(postRequestDto.getFile());
        if(fileCheck.isPresent()) {
            fileProcess(fileCheck.get());
        }
        User user = userRepository.findById(postRequestDto.getUserId()).orElseThrow(NoSuchElementException::new);
        Post post = postRepository.save(postRequestDto.toEntity(user));
        PostResponseDto postResponseDto = PostMapper.INSTANCE.postToPostResponseDto(post);
        return postResponseDto;
    }

    public PostResponseDto findById(final Long id) {
        Post post = postRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return PostMapper.INSTANCE.postToPostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(PostRequestDto postRequestDto, final Long id) {
        Post post = postRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(post.getUser().getId() == postRequestDto.getUserId()) {
            post.update(postRequestDto.getTitle(), postRequestDto.getContent());
        }else {
            //???????????????
        }
        return PostMapper.INSTANCE.postToPostResponseDto(post);
    }

    public Page<QPostResponseDto> findAll(final Pageable pageable, final SearchDto searchDto) {
        return postRepository.findBySearchDto(pageable, searchDto);
    }

/*    public ImmutableMap<String, Object> findAll(final Pageable pageable, SearchDto searchDto) {
        Page<Post> result = postRepository.findAll(pageable);

        ImmutableMap<String, Object> pageAndpostResponseDtos = ImmutableMap.<String, Object>builder()
                .put("posts", PostMapper.INSTANCE.postsToPostResponseDtos(result.getContent()))
                .put("pageInfo", result).build();

        return pageAndpostResponseDtos;
    }*/

    public void fileProcess(MultipartFile multipartFile) throws IOException {
        System.out.println("::DEBUG:: " + multipartFile.getOriginalFilename() + " :: " + multipartFile.getSize() + " :: "/* + Arrays.toString(multipartFile.getBytes())*/);
        String origFilename = multipartFile.getOriginalFilename();
        String savePath = System.getProperty("user.dir") + "\\files";
        if (!new File(savePath).exists()) { /* ????????? ???????????? ????????? ????????? ????????? ?????? */
            boolean check = new File(savePath).mkdir();
            if(!check) {
                System.out.println("::DEBUG:: directory create fail" );
            }
        }
        String filePath = savePath + "\\" + origFilename;
        multipartFile.transferTo(new File(filePath));
    }
}