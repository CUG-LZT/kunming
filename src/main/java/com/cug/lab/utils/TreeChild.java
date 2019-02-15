package com.cug.lab.utils;


public class TreeChild {
    private Long id;
    private String text;
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



    public TreeChild(Long id, String text,String iconCls) {
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
    }
    public TreeChild() {
    }

    @Override
    public String toString() {
        return "TreeChild{" +
                "id=" + id +
                ", text='" + text + '\'' +
              /*  ", iconCls='" + iconCls + '\'' +*/
                '}';
    }
}
