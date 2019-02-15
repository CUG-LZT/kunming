package com.cug.lab.model;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysRole  implements Serializable {

    private static final long serialVersionUID = 1782953758109023915L;

    private Long roleId; //编号
    private String roleName; //角色标识 程序中判断使用,如"admin"
    private String roleDescription; //角色描述,UI界面显示使用
    private Integer roleLevel; //角色权重,去除比当前用户角色权限高的角色--判断权限大小
    private String roleResourceIds; //拥有的资源
    private Boolean roleAvailable = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户



    public SysRole() {
    }

    public SysRole(String roleName, String roleDescription, Boolean roleAvailable,Integer roleLevel) {
        this.roleName = roleName;
        this.roleLevel = roleLevel;
        this.roleDescription = roleDescription;
        this.roleAvailable = roleAvailable;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleResourceIds() {
        return roleResourceIds;
    }

    public void setRoleResourceIds(String roleResourceIds) {
        this.roleResourceIds = roleResourceIds;
    }

    /* 通过roleids字符串设置resourceIdList */
    public List<Long> getResourceIdList() {
        ArrayList<Long> resourceIdList = new ArrayList<Long>();
        if(StringUtils.isEmpty(roleResourceIds)) {

        }else{
            String[] arr = roleResourceIds.split(",");
            if(arr != null || arr.length > 0){
                for (int i = 0 ; i < arr.length ; i++){
                    if(StringUtils.isEmpty(arr[i])){

                    }else {
                        resourceIdList.add(Long.parseLong(arr[i]));
                    }
                }
            }
        }
        return resourceIdList;
    }

    /* 通过resourceIdList设置roleids字符串 */
    public void setResourceIdList(ArrayList<Long> resourceIdList) {
        StringBuilder s = new StringBuilder();

        for(Long resourceId : resourceIdList) {
            if(StringUtils.isEmpty(String.valueOf(resourceId))) {
                continue;
            }
            s.append(resourceId);
            s.append(",");
        }
        this.roleResourceIds =  s.toString();
    }

    public Boolean getRoleAvailable() {
        return roleAvailable;
    }

    public void setRoleAvailable(Boolean roleAvailable) {
        this.roleAvailable = roleAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRole role = (SysRole) o;

        if (roleId != null ? !roleId.equals(role.roleId) : role.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return roleId != null ? roleId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", roleLevel=" + roleLevel +
                ", roleResourceIds='" + roleResourceIds + '\'' +
                ", roleAvailable=" + roleAvailable +
                '}';
    }
}

