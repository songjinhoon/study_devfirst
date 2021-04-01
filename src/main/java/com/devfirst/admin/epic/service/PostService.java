package com.devfirst.admin.epic.service;

import com.devfirst.admin.epic.domain.post.Post;
import com.devfirst.admin.epic.domain.post.PostRepository;
import com.devfirst.admin.epic.domain.mapper.PostMapper;
import com.devfirst.admin.epic.domain.user.UserRepository;
import com.devfirst.admin.epic.dto.PostRequestDto;
import com.devfirst.admin.epic.dto.PostResponseDto;
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
    public Long save(PostRequestDto postRequestDto) throws IOException {
        Optional<MultipartFile> fileCheck = Optional.ofNullable(postRequestDto.getFile());
        if(fileCheck.isPresent()) {
            fileProcess(fileCheck.get());
        }
        Post post = postRequestDto.toEntity();
        post.settingUser(userRepository.findById(postRequestDto.getUserId()).orElseThrow(NoSuchElementException::new));

        return postRepository.save(post).getId();
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

    public Page<PostResponseDto> findAll(final Pageable pageable, final SearchDto searchDto) {
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
        if (!new File(savePath).exists()) { /* 파일이 저장되는 폴더가 없으면 폴더를 생성 */
            boolean check = new File(savePath).mkdir();
            if(!check) {
                System.out.println("::DEBUG:: directory create fail" );
            }
        }
        String filePath = savePath + "\\" + origFilename;
        multipartFile.transferTo(new File(filePath));
    }
}