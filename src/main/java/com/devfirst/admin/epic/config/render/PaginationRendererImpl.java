package com.devfirst.admin.epic.config.render;

import org.springframework.data.domain.Page;

import java.text.MessageFormat;

public class PaginationRendererImpl implements PaginationRenderer{

    private String contextPath;
    private String firstPageLabel;
    private String previousPageLabel;
    private String currentPageLabel;
    private String otherPageLabel;
    private String nextPageLabel;
    private String lastPageLabel;

    private String pageLabel;
    private String nextLabel;

    public PaginationRendererImpl(String contextPath) {
        this.contextPath = contextPath;
        this.pageLabel = "<a class=\"\" data-page=\"{0}\" href=\"#\">{0}</a>";
        this.nextLabel = "<a class=\"\" data-page=\"{0}\" href=\"#\">다음</a>";
        /*this.firstPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_prev2.gif\" alt=\"처음\"/></a>";
        this.previousPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_prev.gif\" alt=\"이전페이지\"/></a>";
        this.currentPageLabel = "<strong><span><a data-page-index=\"{0}\" href=\"#\">{0}</a></span></strong>";
        this.otherPageLabel = "<span><a data-page-index=\"{0}\" href=\"#\">{0}</a></span>";
        this.nextPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_next.gif\" alt=\"다음페이지\"/></a>";
        this.lastPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_next2.gif\" alt=\"마지막\"/></a>"*/;
    }

    @Override
    public String renderPagination(Page page) {
        int pageIndexSize = 5;
        int totalPageCnt = page.getTotalPages();
        int pageSize = page.getSize();
        int currentPageNo = page.getNumber();

        System.out.println("총 페이지수: " + totalPageCnt);
        System.out.println("페이지 크기: " + pageSize);
        System.out.println("현재 페이지: " + currentPageNo);

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<totalPageCnt; i++) {
            if(pageIndexSize < i + 1) {
                stringBuilder.append(MessageFormat.format(this.nextLabel, Integer.toString(i + 1)));
                break;
            }
            stringBuilder.append(MessageFormat.format(this.pageLabel, Integer.toString(i + 1)));
        }

        /*if(page.isFirst()) {
            stringBuilder.append(MessageFormat.format(this.firstPageLabel, Integer.toString(1)));
        }*/

        return stringBuilder.toString();
    }
}