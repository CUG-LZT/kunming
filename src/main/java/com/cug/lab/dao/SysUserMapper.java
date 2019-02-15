package com.cug.lab.dao;

import com.cug.lab.model.SysUser;
import com.cug.lab.utils.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    int createUser(SysUser user);
    int updateUser(SysUser user);
    int deleteUser(Long userId);
    int deleteListUser(List<Integer> ids);
    SysUser findOne(Long userId);
    List<SysUser> findAll(Page page);
    List<SysUser> findAllBy(Map map);
    int getTotle();
    SysUser findByUsername(String username);
    List<SysUser> checkName(SysUser user);
    int getUserTotleBy(SysUser sysUser);
}
