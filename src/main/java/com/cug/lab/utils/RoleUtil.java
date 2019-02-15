package com.cug.lab.utils;

import com.cug.lab.model.SysUser;
import com.cug.lab.service.RoleService;

import javax.annotation.Resource;

public class RoleUtil {

    @Resource
    private static RoleService roleService;

    //将前端传回来的城市id转化为城市名称
    public static void SetRoleIdToString(SysUser sysUser){
        sysUser.setUserRoleIds(roleService.findOne(Long.parseLong(sysUser.getUserRoleIds())).getRoleDescription());
    }
}
