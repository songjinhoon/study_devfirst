package com.devfirst.admin.epic.config.render;

import org.springframework.data.domain.Page;

import java.text.MessageFormat;

public class PaginationRendererImpl implements PaginationRenderer{

    private String contextPath;
    private String pageLabel;
    private String nextLabel;
    private String previousLabel;
    private String otherPageLabel;

    public PaginationRendererImpl(String contextPath) {
        this.contextPath = contextPath;
        this.pageLabel = "<a class=\"pageLabel\" data-page=\"{0}\" href=\"#\">{0}</a>";
        this.otherPageLabel = "<a class=\"\" data-page=\"{0}\" href=\"#\">{0}</a>";;
        this.nextLabel = "<a class=\"\" data-page=\"{0}\" href=\"#\">다음</a>";
        this.previousLabel = "<a class=\"\" data-page=\"{0}\" href=\"#\">이전</a>";
        /*this.firstPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_prev2.gif\" alt=\"처음\"/></a>";
        this.previousPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_prev.gif\" alt=\"이전페이지\"/></a>";
        this.nextPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_next.gif\" alt=\"다음페이지\"/></a>";
        this.lastPageLabel = "<a data-page-index=\"{0}\" href=\"#\" class=\"direction\"><img src=\"" + contextPath + "/publish/1018/images/btn_next2.gif\" alt=\"마지막\"/></a>"*/;
    }
    // 추후에 Page를 다른타입으로 바꿔줘야한다.
    @Override
    public String renderPagination(Page page) {
        int pageIndexSize = 5;
        int totalPageCnt = page.getTotalPages();
        int pageSize = page.getSize();
        int currentPageNo = page.getNumber() + 1;
        int previousLabelPageNo = (currentPageNo / pageIndexSize) * currentPageNo;
        int nextLabelPageNo = previousLabelPageNo + pageIndexSize + 1;

        System.out.println("총 페이지수: " + totalPageCnt);
        System.out.println("페이지 크기: " + pageSize);
        System.out.println("현재 페이지: " + currentPageNo);
        System.out.println("마지막 페이지: " + nextLabelPageNo);

        StringBuilder stringBuilder = new StringBuilder();
        if(previousLabelPageNo != 0 && nextLabelPageNo - 1 != currentPageNo) {
            stringBuilder.append(MessageFormat.format(this.previousLabel, Integer.toString(previousLabelPageNo)));
            if(nextLabelPageNo > totalPageCnt) {
                for(int i=previousLabelPageNo+1; i<totalPageCnt; i++) {
                    if(i == currentPageNo) {
                        stringBuilder.append(MessageFormat.format(this.pageLabel, Integer.toString(i)));
                        continue;
                    }
                    stringBuilder.append(MessageFormat.format(this.otherPageLabel, Integer.toString(i)));
                }
            }else {
                for(int i=previousLabelPageNo+1; i<nextLabelPageNo; i++) {
                    if(i == currentPageNo) {
                        stringBuilder.append(MessageFormat.format(this.pageLabel, Integer.toString(i)));
                        continue;
                    }
                    stringBuilder.append(MessageFormat.format(this.otherPageLabel, Integer.toString(i)));
                }
                stringBuilder.append(MessageFormat.format(this.nextLabel, Integer.toString(nextLabelPageNo)));
            }
        }else {
            if(nextLabelPageNo > totalPageCnt) {
                for(int i=1; i<nextLabelPageNo; i++) {
                    if(i == currentPageNo) {
                        stringBuilder.append(MessageFormat.format(this.pageLabel, Integer.toString(i)));
                        continue;
                    }
                    stringBuilder.append(MessageFormat.format(this.otherPageLabel, Integer.toString(i)));
                }
            }else {
                for(int i=previousLabelPageNo+1; i<nextLabelPageNo; i++) {
                    if(i == currentPageNo) {
                        stringBuilder.append(MessageFormat.format(this.pageLabel, Integer.toString(i)));
                        continue;
                    }
                    stringBuilder.append(MessageFormat.format(this.otherPageLabel, Integer.toString(i)));
                }
                stringBuilder.append(MessageFormat.format(this.nextLabel, Integer.toString(nextLabelPageNo)));
            }
        }

        return stringBuilder.toString();
    }
}