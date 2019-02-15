package com.cug.lab.serviceImpl;

import com.cug.lab.dao.SysRoleMapper;
import com.cug.lab.model.SysRole;
import com.cug.lab.service.ResourceService;
import com.cug.lab.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper roleDao;
    @Autowired
    private ResourceService resourceService;

    public int createRole(SysRole role) {
        return roleDao.createRole(role);
    }

    public int updateRole(SysRole role) {
        return roleDao.updateRole(role);
    }

    public int deleteRole(Long roleId) {
        return roleDao.deleteRole(roleId);
    }

    @Override
    public int deleteListUser(String[] ids) {
        List<Integer> list = new ArrayList<Integer>();
        if(ids!=null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                list.add(Integer.parseInt(ids[i]));
            }
            return roleDao.deleteListUser(list);
        }else {
            return 0;
        }
    }

    @Override
    public SysRole findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    @Override
    public List<SysRole> findListByName(Set<String> set) {
        return roleDao.findListByName(set);
    }

    @Override
    public List<SysRole> findAll() {
        return roleDao.findAll();
    }

    /*通过在用户的信息中拿到的roleids 来通过id循环查询到所有的role信息*/
    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            SysRole role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRoleName());
            }
        }
        return roles;
    }

    /* 通过在用户的信息中拿到的roleids 来通过id循环查询到所有的role信息 并将所有的资源id传给资源服务 */
    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            SysRole role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIdList());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }

    @Override
    public int getTotle() {
        return roleDao.getTotle();
    }

    @Override
    public Boolean checkRoleName(SysRole sysRole) {
        if(roleDao.checkRoleName(sysRole).size() > 0) {
            return false;
        }else {
            return true;
        }
    }
}
