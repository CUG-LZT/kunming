package com.cug.lab.serviceImpl;

import com.cug.lab.dao.SysResourceMapper;
import com.cug.lab.model.SysResource;
import com.cug.lab.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

* @Description:
* @Author:         lzt
* @CreateDate:     2019/1/21
* @Version:        1.0
*/
@Service

public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SysResourceMapper resourceDao;

    @Override
    public int createResource(SysResource resource) {
        return resourceDao.createResource(resource);
    }

    @Override
    public int updateResource(SysResource resource) {
        return resourceDao.updateResource(resource);
    }

    @Override
    public int deleteResource(Long resourceId) {
        return resourceDao.deleteResource(resourceId);
    }

    @Override
    public SysResource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    @Override
    public List<SysResource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public List<SysResource> findChildsByParentId(Long resParentId) {
        return resourceDao.findChildsByParentId(resParentId);
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            SysResource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getResPermission())) {
                permissions.add(resource.getResPermission());
            }
        }
        return permissions;
    }

    /* 查询所有的资源权限 */
    @Override
    public Map<String , List<SysResource>> findMenus(Set<String> permissions) {
        Map<String , List<SysResource>> map = new HashMap<String , List<SysResource>>();
        List<SysResource> allResources = findAll();
        List<SysResource> menus = new ArrayList<SysResource>();
        List<SysResource> pages = new ArrayList<SysResource>();
        for(SysResource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getResType().equals(SysResource.ResourceType.button)) {
                continue;
            }
            if(resource.getResType().equals(SysResource.ResourceType.page)) {
                if(!hasPermission(permissions, resource)) {
                    continue;
                }
                pages.add(resource);
            }
            if(resource.getResType().equals(SysResource.ResourceType.menu)){
                if(!hasPermission(permissions, resource)) {
                    continue;
                }
                menus.add(resource);
            }

        }
        map.put("menus",menus);
        map.put("pages",pages);
        return map;
    }

    @Override
    public int getTotle() {
        return resourceDao.getTotle();
    }

    //传过来的permissions就是用户获得的所有的
    private boolean hasPermission(Set<String> permissions, SysResource resource) {
    	if(StringUtils.isEmpty(resource.getResPermission())) {
            return true;
        }
    	//首先把permissions中带*的：前的 拿出来存起来 hasAll  其他的整体存起来叫做notHasAll
        //看resource.getResPermission().split(:)[0] 在 hasAll在的话 返回true
 		// 如果不存在的话 看在notHasAll中有没有相同的
    	Set<String> hasAll = new HashSet<String>();
    	Set<String> notHasAll = new HashSet<String>();
    	for(String permission : permissions) {
    		int count = 0;
	    	 for(int i=0;i<permission.length();i++)
	         {
	             if(permission.charAt(i)=='*')
	             {
	            	 count++;
	             }
	         }
	    	 if(count > 0) {
	    		 hasAll.add(permission.split(":")[0]);
	    	 }else {
	    		 notHasAll.add(permission);
	    	 }
        }
    	String otherP = resource.getResPermission();
    	int otherPCount = 0;
    	for (int i = 0; i < otherP.length(); i++) {
    		if(otherP.charAt(i)=='*')
            {
    			otherPCount = 1;
    			break;
            }
		}
    	if(otherPCount > 0) { //所轮询到的resource的Permission带*，则在hasAll中看有没有：前相同的
    		if(hasAll.size() > 0) {
        		for (String string : hasAll) {
					if(otherP.split(":")[0].equals(string)) {
						return true;
					}
				}
        	}
    	}else { //所轮询到的resource的Permission不带*，则在noHasAll中看有没有：前相同的
    		if(notHasAll.size() > 0) {
        		for (String string : notHasAll) {
					if(otherP.equals(string)) {
						return true;
					}
				}
        	}
    	}
    	return false;
    }
}
