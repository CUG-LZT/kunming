package com.cug.lab.model;

import java.io.Serializable;

public class SysResource implements Serializable {

    private static final long serialVersionUID = 8300548177048947510L;

    private Long resId; //编号
    private String resName; //资源名称
    private ResourceType resType = ResourceType.menu; //资源类型
    private String resUrl; //资源路径
    private String resPermission; //权限字符串
    private Long resParentId; //父编号
    private String resParentIds; //父编号列表
    private String resIcon; //图标
    private Boolean resAvailable = Boolean.FALSE;

    public static enum ResourceType {
        menu("菜单"),page("页面"), button("按钮");

        private final String info;
        private ResourceType(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public ResourceType getResType() {
        return resType;
    }

    public void setResType(ResourceType resType) {
        this.resType = resType;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getResPermission() {
        return resPermission;
    }

    public void setResPermission(String resPermission) {
        this.resPermission = resPermission;
    }

    public Long getResParentId() {
        return resParentId;
    }

    public void setResParentId(Long resParentId) {
        this.resParentId = resParentId;
    }

    public String getResParentIds() {
        return resParentIds;
    }

    public void setResParentIds(String resParentIds) {
        this.resParentIds = resParentIds;
    }

    public String getResIcon() {
        return resIcon;
    }

    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

    public Boolean getResAvailable() {
        return resAvailable;
    }

    public void setResAvailable(Boolean resAvailable) {
        this.resAvailable = resAvailable;
    }

    public boolean isRootNode() {
        return resParentId == 0;
    }

    public String makeSelfAsParentIds() {
        return getResParentIds() + getResId() + "/";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysResource resource = (SysResource) o;

        if (resId != null ? !resId.equals(resource.resId) : resource.resId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return resId != null ? resId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SysResource{" +
                "resId=" + resId +
                ", resName='" + resName + '\'' +
                ", resType=" + resType +
                ", resUrl='" + resUrl + '\'' +
                ", resPermission='" + resPermission + '\'' +
                ", resParentId=" + resParentId +
                ", resParentIds='" + resParentIds + '\'' +
                ", resIcon='" + resIcon + '\'' +
                ", resAvailable=" + resAvailable +
                '}';
    }
}
