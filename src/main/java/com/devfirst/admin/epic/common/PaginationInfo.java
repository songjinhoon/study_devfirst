package com.devfirst.admin.epic.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class PaginationInfo {

    private Pageable pageable;

    private int totalPageCnt;

    @Builder
    public PaginationInfo(Pageable pageable, int totalPageCnt) {
        this.pageable = pageable;
        this.totalPageCnt = totalPageCnt;
    }
}
