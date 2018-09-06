package com.zehao.Chaozhou.param;

/**
 * Created by MS on 2018/9/3.
 */
public class PageParam {

    private String draw;
    private String startIndex;
    private String pageSize;
    private String orderColumn;
    private String orderDir;

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "draw='" + draw + '\'' +
                ", startIndex='" + startIndex + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", orderColumn='" + orderColumn + '\'' +
                ", orderDir='" + orderDir + '\'' +
                '}';
    }
}
