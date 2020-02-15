package com.xiaowei.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxw on 2020/2/12
 */
@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCnt, Integer page, Integer size) {
        if (totalCnt % size == 0) {
            totalPage = totalCnt / size;
        } else {
            totalPage = totalCnt / size + 1;
        }
        if (page < 1) {
            page = 1;
        } else if (page > totalPage) {
            page = totalPage;
        }

        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i >= 1) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }
}
