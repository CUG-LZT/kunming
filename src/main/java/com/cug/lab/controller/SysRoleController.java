package com.cug.lab.controller;


import com.cug.lab.model.SysResource;
import com.cug.lab.model.SysRole;
import com.cug.lab.model.SysUser;
import com.cug.lab.service.ResourceService;
import com.cug.lab.service.RoleService;
import com.cug.lab.service.UserService;
import com.cug.lab.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("sys/role")
public class SysRoleController {


    @Resource
    private  UserService userService;

    @Resource
    private  RoleService roleService;

    @Resource
    private ResourceService resourceService;

    @RequestMapping("/role.page")
    public String user(Model model){
        return "sys/role/role";
    }

    @ResponseBody
    @RequestMapping("/selectAllRole.json")
    public EasyuiData<SysRole> selectAllRole(){
        EasyuiData<SysRole> easyuiData = new EasyuiData<SysRole>();
        easyuiData.setTotal(roleService.getTotle());
        easyuiData.setRows(roleService.findAll());
        return easyuiData;
    }


    @ResponseBody
    @RequestMapping("/insertRole.json")
    public MsgToPage<SysRole> insertRole(SysRole sysRole) {
        MsgToPage<SysRole> msgToPage = new MsgToPage<SysRole>();
        if(checkRoleName(sysRole)) {
            sysRole.setRoleLevel(calculateResLrvel(sysRole.getResourceIdList()));
            sysRole.setRoleAvailable(true);
            if (roleService.createRole(sysRole) > 0) {
                msgToPage.setCode(RespondCode.SUCCESS);
                msgToPage.setMsg("添加角色成功！");
            } else {
                msgToPage.setCode(RespondCode.FAIL);
                msgToPage.setMsg("添加失败！");
            }
        }else{
            msgToPage.setCode(RespondCode.RENAME);
            msgToPage.setMsg("此角色名已存在！");
        }
        return msgToPage;
    }


    @ResponseBody
    @RequestMapping("/deleteOneRole.json")
    public MsgToPage<SysRole> deleteOneRole(SysRole sysRole) {
        MsgToPage<SysRole> msgToPage = new MsgToPage<SysRole>();
        if(roleService.deleteRole(sysRole.getRoleId()) > 0){
            msgToPage.setCode(RespondCode.SUCCESS);
            msgToPage.setMsg("删除角色成功！");
        }else{
            msgToPage.setCode(RespondCode.FAIL);
            msgToPage.setMsg("删除失败！");
        }
        return msgToPage;
    }

    @ResponseBody
    @RequestMapping("/deleteListRole.json")
    public MsgToPage<SysRole> deleteListUser(String[] ids) {
        MsgToPage<SysRole> msgToPage = new MsgToPage<SysRole>();
        int count = roleService.deleteListUser(ids);
        if(count > 0) {
            msgToPage.setCode(200);
            msgToPage.setMsg("删除"+count+"条用户成功！");
        }else {
            msgToPage.setCode(404);
            msgToPage.setMsg("删除用户失败！");
        }
        return msgToPage;
    }

    @ResponseBody
    @RequestMapping("/updateRole.json")
    public MsgToPage<SysRole> updateRole(SysRole sysRole) {
        MsgToPage<SysRole> msgToPage = new MsgToPage<SysRole>();
        sysRole.setRoleLevel(calculateResLrvel(sysRole.getResourceIdList()));
        int count = roleService.updateRole(sysRole);
        if(count == 1) {
            msgToPage.setCode(RespondCode.SUCCESS);
            msgToPage.setMsg("编辑"+sysRole.getRoleDescription()+"用户成功！");
        }else {
            msgToPage.setCode(RespondCode.FAIL);
            msgToPage.setMsg("编辑"+sysRole.getRoleDescription()+"用户失败！");
        }
        return msgToPage;
    }

    public int calculateResLrvel(List<Long> resIds){
        int result = 1;
        int pageNum = 0;
        int buttonNum = 0;
        for(Long id : resIds){
            if(id > 99){
                pageNum++;
            }else if(id > 999){
                buttonNum++;
            }
        }
        return pageNum * 4 + buttonNum;
    }


    /*
      构建资源树
         1.获取模块（menu）页面采用数据结构--EasyuiResMenu
         2.获取页面（page）添加到模块中  页面采用数据结构--EasyuiResPage
         3.获取按钮（button）添加到页面中 按钮采用数据结构--EasyuiResButton
    */
    //todo
    //循环中有些可以直接跳出
    @ResponseBody
    @RequestMapping("/roleResourceIdsTreeData.json")
    public List<TreeFather<TreeFather>> roleResourceIdsTreeData(){
        //构建总模块链表
        List<TreeFather<TreeFather>> easyuiResList = new ArrayList<TreeFather<TreeFather>>();
        //获取所有资源
        List<SysResource> resourceList = resourceService.findAll();
        //根加进list
        for (SysResource resource : resourceList) {
            if(resource.isRootNode()){
                TreeFather<TreeFather> treeFather =
                        new TreeFather<TreeFather>(
                                resource.getResId(),
                                resource.getResName()+ " - " +resource.getResId(),
                                "open",
                                "icon-large-chart");
                easyuiResList.add(treeFather);
                break;
            }
        }
        //模块加进根元素
        for (SysResource resource : resourceList) {
            if((resource.getResType().equals(SysResource.ResourceType.menu)) && !(resource.isRootNode())){
                TreeFather<TreeFather> Menu =
                        new TreeFather<TreeFather>(
                                resource.getResId(),
                                resource.getResName()+ " - " +resource.getResId(),
                                "closed",
                                "icon-large-clipart");
                easyuiResList.get(0).getChildren().add(Menu);
            }
        }
        //页面加进根元素
        for (SysResource resource : resourceList) {
            if((resource.getResType().equals(SysResource.ResourceType.page))){
                for(TreeFather<TreeFather> Menu : easyuiResList.get(0).getChildren()){
                    if(resource.getResParentId() == Menu.getId()){ //资源父ID == 链表模块id
                        TreeFather<TreeChild> Page =
                                new TreeFather<TreeChild>(
                                        resource.getResId(),
                                        resource.getResName()+ " - " +resource.getResId(),
                                        "closed",
                                        "icon-large-picture");
                        Menu.getChildren().add(Page);
                    }
                }
            }
        }
        //按钮加进根元素
        for (SysResource resource : resourceList) {
            if((resource.getResType().equals(SysResource.ResourceType.button))){
                for(TreeFather<TreeFather> Menu : easyuiResList.get(0).getChildren()){
                    if((resource.getResId()/100) == Menu.getId()){
                        for(TreeFather<TreeChild> page : Menu.getChildren()){
                            if((resource.getResId()/10) == page.getId()){
                                TreeChild button =
                                                new TreeChild(
                                                        resource.getResId(),
                                                        resource.getResName()+ " - " +resource.getResId(),
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
        for(TreeFather<TreeFather> meun : easyuiResList.get(0).getChildren()){
            if(meun.getChildren().size() < 1){
                meun.setState("open");
            }else{
                for(TreeFather<TreeFather> page : meun.getChildren()){
                    if(page.getChildren().size() < 1){
                        page.setState("open");
                    }
                }
            }
        }
        return easyuiResList;
    }


    //检查用户名是否已经存在
    public Boolean checkRoleName(SysRole sysRole){
        return roleService.checkRoleName(sysRole);
    }
}
