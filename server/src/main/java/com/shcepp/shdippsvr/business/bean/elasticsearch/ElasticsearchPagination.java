package com.shcepp.shdippsvr.business.bean.elasticsearch;

import java.util.List;

/**
 * 分页类
 * @author kzhou
 * @date2018/11/20
 */
public class ElasticsearchPagination<T> {

    /*总条数*/
    private long totalSize;
    /*当前页数，页面下标从0开始*/
    private int currentPage;
    /*查询的内容*/
    private List<T> contentList;
    /*异常信息*/
    private String errInfo;
    /*查询开销*/
    private Long cost;
    /*单页条数*/
    private int pageSize;

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getContentList() {
        return contentList;
    }

    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
