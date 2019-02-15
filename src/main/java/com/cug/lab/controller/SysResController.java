package com.cug.lab.controller;


import com.cug.lab.model.SysResource;
import com.cug.lab.model.SysRole;
import com.cug.lab.model.SysUser;
import com.cug.lab.service.ResourceService;
import com.cug.lab.service.RoleService;
import com.cug.lab.service.UserService;
import com.cug.lab.utils.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;


@Controller
@RequestMapping("sys/res")
public class SysResController {


    @Resource
    private  UserService userService;

    @Resource
    private  RoleService roleService;

    @Resource
    private ResourceService resourceService;

    @RequestMapping("/res.page")
    public String user(Model model){
        return "sys/res/res";
    }

    @ResponseBody
    @RequestMapping("/createResource.json")
    public MsgToPage<SysResource> createResource(SysResource sysResource){
        MsgToPage<SysResource> sysResourceMsgToPage = new MsgToPage<SysResource>();
        //根据父节点获取已有子节点id组合然后计算出最大值 加1 赋给id 但是范围在0-9

        List<SysResource> resourceList = resourceService.findChildsByParentId(sysResource.getResParentId());
        //去URL空格
        sysResource.setResUrl(sysResource.getResUrl().replaceAll(" ", ""));
        Long resId = 0L;
        if(resourceList.size() > 0 ){
            Long[] ids = new Long[resourceList.size()];
            for (int i = 0; i < ids.length; i++) {
                SysResource sysRes = resourceList.get(i);
                ids[i] = sysRes.getResId();
            }
            resId = (Long) Collections.max(Arrays.asList(ids)) + 1;
        }else{
            resId = sysResource.getResParentId() * 10;
        }
        if(resourceList.size() > 9){
            sysResourceMsgToPage.setCode(RespondCode.FAIL);
            sysResourceMsgToPage.setMsg("添加失败,其子节点已到最大值");
        }else{
            sysResource.setResId(resId);
            if((resourceService.createResource(sysResource)) > 0){
                sysResourceMsgToPage.setCode(RespondCode.SUCCESS);
                sysResourceMsgToPage.setMsg("添加资源" + sysResource.getResName() + "成功");
            }else{
                sysResourceMsgToPage.setCode(RespondCode.FAIL);
                sysResourceMsgToPage.setMsg("添加失败");
            }
        }
        return sysResourceMsgToPage;
    }

    @ResponseBody
    @RequestMapping("/updateResource.json")
    public MsgToPage<SysResource> updateResource(SysResource sysResource){
        MsgToPage<SysResource> sysResourceMsgToPage = new MsgToPage<SysResource>();
        //去URL空格
        sysResource.setResUrl(sysResource.getResUrl().replaceAll(" ", ""));
        if((resourceService.updateResource(sysResource)) == 1){
            sysResourceMsgToPage.setCode(RespondCode.SUCCESS);
            sysResourceMsgToPage.setMsg("更新资源" + sysResource.getResName() + "成功!");
        }else{
            sysResourceMsgToPage.setCode(RespondCode.FAIL);
            sysResourceMsgToPage.setMsg("更新资源失败!");
        }
        return sysResourceMsgToPage;
    }
    @ResponseBody
    @RequestMapping("/deleteOneRes.json")
    public MsgToPage<SysResource> deleteOneRes(SysResource sysResource){
        MsgToPage<SysResource> sysResourceMsgToPage = new MsgToPage<SysResource>();
        if((resourceService.deleteResource(sysResource.getResId())) == 1){
            sysResourceMsgToPage.setCode(RespondCode.SUCCESS);
            sysResourceMsgToPage.setMsg("删除资源成功!");
        }else{
            sysResourceMsgToPage.setCode(RespondCode.FAIL);
            sysResourceMsgToPage.setMsg("删除失败!");
        }
        return sysResourceMsgToPage;
    }

    @ResponseBody
    //@RequiresPermissions("pageresource:view")
    @RequestMapping("/selectAllRes.json")
    public List<TreegridResFather<TreegridResFather>> selectAllRes(){
        //获取所有资源
        List<SysResource> resourceList = resourceService.findAll();
        //构建总模块链表
        List<TreegridResFather<TreegridResFather>> treegridResRootList = new ArrayList<TreegridResFather<TreegridResFather>>();
        for (SysResource resource : resourceList) {
            if(resource.isRootNode()){
                TreegridResFather<TreegridResFather> treegridResFather =
                        new TreegridResFather<TreegridResFather>(
                                resource.getResId(),
                                resource.getResName(),
                                resource.getResType(),
                                resource.getResUrl(),
                                resource.getResPermission(),
                                resource.getResParentId(),
                                resource.getResParentIds(),
                                resource.getResIcon(),
                                resource.getResAvailable(),
                                "icon-large-chart");
                treegridResFather.setState("open");
                treegridResRootList.add(treegridResFather);
                break;
            }
        }
        //模块加进根元素
        for (SysResource resource : resourceList) {
            if((resource.getResType().equals(SysResource.ResourceType.menu)) && !(resource.isRootNode())){
                TreegridResFather<TreegridResFather> Menu =
                        new TreegridResFather<TreegridResFather>(
                                resource.getResId(),
                                resource.getResName(),
                                resource.getResType(),
                                resource.getResUrl(),
                                resource.getResPermission(),
                                resource.getResParentId(),
                                resource.getResParentIds(),
                                resource.getResIcon(),
                                resource.getResAvailable(),
                                "icon-large-clipart");
                treegridResRootList.get(0).getChildren().add(Menu);
            }
        }
        //页面加进根元素
        for (SysResource resource : resourceList) {
            if((resource.getResType().equals(SysResource.ResourceType.page))){
                for(TreegridResFather<TreegridResFather> Menu : treegridResRootList.get(0).getChildren()){
                    if(resource.getResParentId() == Menu.getResId()){
                        TreegridResFather<TreegridResChild> Page =
                                new TreegridResFather<TreegridResChild>(
                                        resource.getResId(),
                                        resource.getResName(),
                                        resource.getResType(),
                                        resource.getResUrl(),
                                        resource.getResPermission(),
                                        resource.getResParentId(),
                                        resource.getResParentIds(),
                                        resource.getResIcon(),
                                        resource.getResAvailable(),
                                        "icon-large-picture");
                        Menu.getChildren().add(Page);
                    }
                }
            }
        }
        //按钮加进根元素
        for (SysResource resource : resourceList) {
            if((resource.getResType().equals(SysResource.ResourceType.button))){
                for(TreegridResFather<TreegridResFather> Menu : treegridResRootList.get(0).getChildren()){
                    if((resource.getResId()/100) == Menu.getResId()){
                        for(TreegridResFather<TreegridResChild> page : Menu.getChildren()){
                            if((resource.getResId()/10) == page.getResId()){
                                TreegridResChild button =
                                        new TreegridResChild(
                                                resource.getResId(),
                                                resource.getResName(),
                                                resource.getResType(),
                                                resource.getResUrl(),
                                                resource.getResPermission(),
                                                resource.getResParentId(),
                                                resource.getResParentIds(),
                                                resource.getResIcon(),
                                                resource.getResAvailable(),
                                                "icon-large-shapes");
                                page.getChildren().add(button);
                            }
                        }
                    }
                }
            }
        }
        /*当存在children为空的时候，前端显示会出现异常---需要将非叶节点的statis设置为open或者空*/
        //考虑换乘递归
        //todo
        for(TreegridResFather<TreegridResFather> meun : treegridResRootList.get(0).getChildren()){
            if(meun.getChildren().size() < 1){
                meun.setState("open");
            }else{
                for(TreegridResFather<TreegridResFather> page : meun.getChildren()){
                    if(page.getChildren().size() < 1){
                        page.setState("open");
                    }
                }
            }
        }
        return treegridResRootList;
    }


}
