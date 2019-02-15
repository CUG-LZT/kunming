package com.cug.lab.service;

import com.cug.lab.model.SysResource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**

* @Description:    资源操作service层
* @Author:         lzt
* @CreateDate:     2019/1/21
* @Version:        1.0
*/
public interface ResourceService {


    public int createResource(SysResource resource);
    public int updateResource(SysResource resource);
    public int deleteResource(Long resourceId);

    SysResource findOne(Long resourceId);
    List<SysResource> findAll();

    List<SysResource> findChildsByParentId(Long resParentId);

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    Map<String , List<SysResource>> findMenus(Set<String> permissions);

    /**
     * 获取资源总数
     * @param
     * @return
     */
    int getTotle();


}
