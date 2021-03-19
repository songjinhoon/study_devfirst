package com.devfirst.admin.epic.config.render;

import org.springframework.data.domain.Page;

public class PaginationRendererImpl implements PaginationRenderer{

    private String contextPath;
    private String firstPageLabel;
    private String previousPageLabel;
    private String currentPageLabel;
    private String otherPageLabel;
    private String nextPageLabel;
    private String lastPageLabel;

    public PaginationRendererImpl(String contextPath) {
        this.contextPath = contextPath;
        this.firstPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_prev2.gif\" alt=\"처음\"/></a>";
        this.previousPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_prev.gif\" alt=\"이전페이지\"/></a>";
        this.currentPageLabel = "<strong><span><a data-page-index=\"{0}\" href=\"#\">{0}</a></span></strong>";
        this.otherPageLabel = "<span><a data-page-index=\"{0}\" href=\"#\">{0}</a></span>";
        this.nextPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_next.gif\" alt=\"다음페이지\"/></a>";
        this.lastPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_next2.gif\" alt=\"마지막\"/></a>";
    }

    @Override
    public String renderPagination(Page page) {

        System.out.println(page);

        return null;
    }
}
