package com.devfirst.admin.epic.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {

    private int page = 1;

/*    @Builder
    public SearchDto(int page) {
        this.page = page;
    }*/
}
