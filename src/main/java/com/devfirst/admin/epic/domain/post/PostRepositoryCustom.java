package com.devfirst.admin.epic.domain.post;

import com.devfirst.admin.epic.dto.QPostResponseDto;
import com.devfirst.admin.epic.dto.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findByTitle(String title); // 테스트용

    Page<QPostResponseDto> findBySearchDto(Pageable pageable, SearchDto searchDto);
}
