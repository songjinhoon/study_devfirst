package com.devfirst.admin.epic.config.render;

import com.devfirst.admin.epic.common.PaginationInfo;
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
        this.otherPageLabel = "<a class=\"otherLabel\" data-page=\"{0}\" href=\"#\">{0}</a>";;
        this.nextLabel = "<a class=\"nextLabel\" data-page=\"{0}\" href=\"#\">다음</a>";
        this.previousLabel = "<a class=\"previousLabel\" data-page=\"{0}\" href=\"#\">이전</a>";
    }
    // 추후에 Page를 다른타입으로 바꿔줘야한다.
    @Override
    public String renderPagination(PaginationInfo paginationInfo) {
        int pageIndexSize = 5;
        int totalPageCnt = paginationInfo.getTotalPageCnt();
        int pageSize = paginationInfo.getPageable().getPageSize();
        int currentPageNo = paginationInfo.getPageable().getPageNumber() + 1;
        int previousLabelPageNo;
        int nextLabelPageNo;
        if((currentPageNo % pageIndexSize) == 0){
            previousLabelPageNo = currentPageNo - pageIndexSize;
            nextLabelPageNo = currentPageNo + 1;
        }else {
            previousLabelPageNo = (currentPageNo / pageIndexSize) * pageIndexSize;
            nextLabelPageNo = previousLabelPageNo + pageIndexSize + 1;
        }
        System.out.println("총 페이지수: " + totalPageCnt);
        System.out.println("페이지 크기: " + pageSize);
        System.out.println("현재 페이지: " + currentPageNo);
        System.out.println("이전 페이지: " + previousLabelPageNo);
        System.out.println("다음 페이지: " + nextLabelPageNo);

        StringBuilder stringBuilder = new StringBuilder();
        if(currentPageNo > pageIndexSize) stringBuilder.append(MessageFormat.format(this.previousLabel, Integer.toString(previousLabelPageNo)));
        if(nextLabelPageNo > totalPageCnt) {
            pageLabelLoop(stringBuilder, currentPageNo, previousLabelPageNo, totalPageCnt+1);
        }else {
            pageLabelLoop(stringBuilder, currentPageNo, previousLabelPageNo, nextLabelPageNo);
            stringBuilder.append(MessageFormat.format(this.nextLabel, Integer.toString(nextLabelPageNo)));
        }

        return stringBuilder.toString();
    }

    public void pageLabelLoop(StringBuilder stringBuilder, int currentPageNo, int previousLabelPageNo, int limitNum) {
        for(int i=previousLabelPageNo+1; i<limitNum; i++) {
            if(i == currentPageNo) {
                stringBuilder.append(MessageFormat.format(this.pageLabel, Integer.toString(i)));
                continue;
            }
            stringBuilder.append(MessageFormat.format(this.otherPageLabel, Integer.toString(i)));
        }
    }
}