package com.zehao.Chaozhou.param;

public class UserQueryParam {

    private String fuzzySearch;
    private String fuzzy;
    private String uname;
    private String phoneNumber;
    private String sex;
    //private String status;
    //private String role;
    //private String joinData;
    private String draw;
    private String startIndex;
    private String pageSize;
    private String orderColumn;
    private String orderDir;

    public String getFuzzySearch() {
        return fuzzySearch;
    }

    public void setFuzzySearch(String fuzzySearch) {
        this.fuzzySearch = fuzzySearch;
    }

    public String getFuzzy() {
        return fuzzy;
    }

    public void setFuzzy(String fuzzy) {
        this.fuzzy = fuzzy;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

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
        return "UserQueryParam{" +
                "fuzzySearch='" + fuzzySearch + '\'' +
                ", fuzzy='" + fuzzy + '\'' +
                ", uname='" + uname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", draw='" + draw + '\'' +
                ", startIndex='" + startIndex + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", orderColumn='" + orderColumn + '\'' +
                ", orderDir='" + orderDir + '\'' +
                '}';
    }
}
