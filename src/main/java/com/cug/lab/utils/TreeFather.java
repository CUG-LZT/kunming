package com.cug.lab.utils;


import java.util.ArrayList;
import java.util.List;

public class TreeFather<T> {
    private Long id;
    private String text;

    private String state;
    private List<T> children = new ArrayList<T>();
    private String iconCls;

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public TreeFather(Long id, String text, String state,String iconCls) {
        this.id = id;
        this.text = text;
        this.state = state;
        this.iconCls = iconCls;
    }
    public TreeFather() {
    }

    @Override
    public String toString() {
        return "TreeFather{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                ", children=" + children +
                '}';
    }
}
