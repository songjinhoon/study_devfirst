package com.devfirst.admin.epic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchDto {

    private int page;

    @Builder
    public SearchDto(int page) {
        this.page = page;
    }
}
