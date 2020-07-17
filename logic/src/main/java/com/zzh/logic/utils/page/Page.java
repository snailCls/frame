package com.zzh.logic.utils.page;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private List<T> pageList;
    private PageData pageData;

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public PageData getPageData() {
        return pageData;
    }

    public void setPageData(PageData pageData) {
        this.pageData = pageData;
    }
}
