package com.tea101g3.entity;



public class ListBean {
    private Integer id;
    private String title;
    private Integer orderBy;
    private Integer priority;

    @Override
    public String toString() {
        return "ListBean{" +
                "title='" + title + '\'' +
                ", orderBy=" + orderBy +
                ", priority=" + priority +
                '}';
    }



    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }



    public String toJson() {
        return "{" +
                "title: '" + title + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}