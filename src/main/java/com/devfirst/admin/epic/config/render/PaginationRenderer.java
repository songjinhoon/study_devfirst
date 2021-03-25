package com.devfirst.admin.epic.config.render;

import com.devfirst.admin.epic.common.PaginationInfo;
import org.springframework.data.domain.Page;

public interface PaginationRenderer {
    String renderPagination(PaginationInfo paginationInfo);
}
