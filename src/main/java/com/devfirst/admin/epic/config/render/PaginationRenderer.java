package com.devfirst.admin.epic.config.render;

import org.springframework.data.domain.Page;

public interface PaginationRenderer {
    String renderPagination(Page page);
}
