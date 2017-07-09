package com.sywl.support;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Huzl
 * @version 1.0.0
 */
public class BaseResponse<T> {
    @JsonProperty("result")
    private String result = "success";
    private String message = "";
    private int totalRecordNum; //总记录数
    private int pages;//总页数
    private int pageNo;//第几页
    private T data;

//
//    public BaseResponse(T detail) {
//        this.detail = detail;
//        if (ClassUtils.isAssignable(detail.getClass(), Pagination.class)) {
//            //如果返回的是分页的对象，则吧分页的数据提前放到response
//            Pagination p = (Pagination) detail;
//            this.totalRecordNum = p.getTotalRecordNum();
//            this.pageNo = p.getPageNo();
//            this.pages = p.getPages();
//
//            List<T> dataList = p.getList() != null ? p.getList() : new ArrayList<T>();
//            Map<String, Object> dataListMap = new HashMap<String, Object>();
//            dataListMap.putAll(p.getAttrs());
//            dataListMap.put("list", dataList);
//            this.detail = (T) dataListMap;
//        }
//    }

    public BaseResponse() {
    }


}
