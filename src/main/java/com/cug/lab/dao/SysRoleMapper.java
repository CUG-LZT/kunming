package com.cug.lab.dao;

import com.cug.lab.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysRoleMapper {

    public int createRole(SysRole role);
    public int updateRole(SysRole role);
    public int deleteRole(Long roleId);
    int deleteListUser(List<Integer> ids);
    public SysRole findOne(Long roleId);
    public List<SysRole> findListByName(@Param("roles") Set<String> set);
    public List<SysRole> findAll();
    public int getTotle();
    List<SysRole> checkRoleName(SysRole sysRole);
}
