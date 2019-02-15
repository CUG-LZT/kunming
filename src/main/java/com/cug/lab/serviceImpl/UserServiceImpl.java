package com.cug.lab.serviceImpl;

import com.cug.lab.dao.SysUserMapper;
import com.cug.lab.model.SysUser;
import com.cug.lab.service.RoleService;
import com.cug.lab.service.UserService;
import com.cug.lab.utils.Page;
import com.cug.lab.utils.PasswordHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userDao;
    @Autowired
    private RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    public int createUser(SysUser sysUser) {
        //加密密码
        return userDao.createUser(PasswordHelper.getMD5String(sysUser));
    }

    @Override
    public int updateUser(SysUser user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Long userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public int deleteListUser(String[] ids) {
        List<Integer> list = new ArrayList<Integer>();
        if(ids!=null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                list.add(Integer.parseInt(ids[i]));
            }
            return userDao.deleteListUser(list);
        }else {
            return 0;
        }
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        SysUser sysUser =userDao.findOne(userId);
        sysUser.setUserPsd(newPassword);
        userDao.updateUser(PasswordHelper.getMD5String(sysUser));
    }

    @Override
    public SysUser findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public List<SysUser> findAll(Page page) {
        page.setCourent();
        return userDao.findAll(page);
    }

    @Override
    public List<SysUser> findAllBy(Page page, SysUser sysUser) {
        Map<String , Object> map = new HashMap<String , Object>();
        page.setCourent();
        map.put("page" , page);
        map.put("sysUser" , sysUser);
        return userDao.findAllBy(map);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        SysUser user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(user.getRoleIdList().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        SysUser user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(user.getRoleIdList().toArray(new Long[0]));
    }

    @Override
    public int getUserTotle() {
        return userDao.getTotle();
    }

    @Override
    public Boolean checkName(SysUser user) {
        if(userDao.checkName(user).size() > 0) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public int getUserTotleBy(SysUser sysUser) {
        return  userDao.getUserTotleBy(sysUser);
    }

}

