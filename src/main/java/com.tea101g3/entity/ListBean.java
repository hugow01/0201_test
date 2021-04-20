package com.tea101g3.entity;


public class ListBean {
    private String id;
    private Integer checked;
    private String title;
    private Integer orderBy;
    private Integer priority;

    @Override
    public String toString() {
        return "ListBean{" +
                "id=" + id + '\'' +
                "checked=" + checked + '\'' +
                ",title='" + title + '\'' +
                ", orderBy=" + orderBy +
                ", priority=" + priority +
                '}';
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Integer getChecked() {  return checked; }

    public void setChecked(Integer checked) { this.checked = checked; }

}