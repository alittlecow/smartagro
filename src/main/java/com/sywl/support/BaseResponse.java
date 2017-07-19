package com.sywl.support;

import com.github.pagehelper.Page;
import org.apache.commons.lang3.ClassUtils;

/**
 * @author Huzl
 * @version 1.0.0
 */
public class BaseResponse<T> {
    private String result = "success";
    private String message = "";
    private long totalRecordNum; //总记录数
    private int pages;//总页数
    private int pageNo;//第几页
    private T data;

    public BaseResponse(T data) {
        this.data = data;
        if (ClassUtils.isAssignable(data.getClass(), Page.class)) {
            Page p = (Page) data;
            this.totalRecordNum = p.getTotal();
            this.pages = p.getPages();
            this.pageNo = p.getPageNum();
        }
    }


    public BaseResponse() {
    }

    public BaseResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotalRecordNum() {
        return totalRecordNum;
    }

    public void setTotalRecordNum(long totalRecordNum) {
        this.totalRecordNum = totalRecordNum;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
