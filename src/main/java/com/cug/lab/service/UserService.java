package com.cug.lab.service;

import com.cug.lab.model.SysUser;
import com.cug.lab.utils.Page;

import java.util.List;
import java.util.Set;

public interface UserService {
    /**
     * 创建用户
     * @param user
     */
    public int createUser(SysUser user);

    public int updateUser(SysUser user);

    public int deleteUser(Long userId);

    public int deleteListUser(String[] ids);
    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);


    SysUser findOne(Long userId);

    List<SysUser> findAll(Page page);

    List<SysUser> findAllBy(Page page,SysUser sysUser);
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUser findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

    /**
     * 获取用户总数
     * @param
     * @return
     */
    public int getUserTotle();

    /**
     * 根据用户名查找其权限
     * @param
     * @return
     */
    public Boolean checkName(SysUser user);

    int getUserTotleBy(SysUser sysUser);
}
