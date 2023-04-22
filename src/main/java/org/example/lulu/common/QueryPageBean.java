package org.example.lulu.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class QueryPageBean implements Serializable {
    private Integer currentPage;
    private Integer pageSize;

    public void setQueryString(String queryString) {
        this.queryString = String.valueOf(queryString);
    }

    public String getQueryString() {
        return queryString;
    }

    private String queryString;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
