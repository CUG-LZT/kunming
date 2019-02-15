package com.cug.lab.utils;


import com.cug.lab.model.SysResource;

import java.util.ArrayList;
import java.util.List;

public class TreegridResChild {
    private Long resId; //编号
    private String resName; //资源名称
    private SysResource.ResourceType resType ; //资源类型
    private String resUrl; //资源路径
    private String resPermission; //权限字符串
    private Long resParentId; //父编号
    private String resParentIds; //父编号列表
    private String resIcon; //图标
    private Boolean resAvailable ;

    private String iconCls;

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

    public SysResource.ResourceType getResType() {
        return resType;
    }

    public void setResType(SysResource.ResourceType resType) {
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public TreegridResChild() {

    }

    public TreegridResChild(Long resId, String resName, SysResource.ResourceType resType, String resUrl, String resPermission, Long resParentId, String resParentIds, String resIcon, Boolean resAvailable,String iconCls) {
        this.resId = resId;
        this.resName = resName;
        this.resType = resType;
        this.resUrl = resUrl;
        this.resPermission = resPermission;
        this.resParentId = resParentId;
        this.resParentIds = resParentIds;
        this.resIcon = resIcon;
        this.resAvailable = resAvailable;
        this.iconCls = iconCls;
    }

    @Override
    public String toString() {
        return "TreegridResChild{" +
                "resId=" + resId +
                ", resName='" + resName + '\'' +
                ", resType=" + resType +
                ", resUrl='" + resUrl + '\'' +
                ", resPermission='" + resPermission + '\'' +
                ", resParentId=" + resParentId +
                ", resParentIds='" + resParentIds + '\'' +
                ", resIcon='" + resIcon + '\'' +
                ", resAvailable=" + resAvailable +
                ", iconCls='" + iconCls + '\'' +
                '}';
    }
}
