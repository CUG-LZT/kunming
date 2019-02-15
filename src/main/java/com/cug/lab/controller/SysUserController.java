package com.cug.lab.controller;


import com.cug.lab.Constants;
import com.cug.lab.model.SysRole;
import com.cug.lab.model.SysUser;
import com.cug.lab.service.ResourceService;
import com.cug.lab.service.RoleService;
import com.cug.lab.service.UserService;
import com.cug.lab.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


@Controller
@RequestMapping("sys/user")
public class SysUserController {


    @Resource
    private  UserService userService;

    @Resource
    private  RoleService roleService;

    @Resource
    private ResourceService resourceService;

    @RequestMapping("/user.page")
    public String user(Model model){
        return "sys/user/user";
    }

    /**
     * 搜索用户群
     * @param page  分页参数
     * @param value 搜索框内容或省份查询的选择框内容或角色查询的内容
     * @param name  搜索框搜索条件或省份查询的条件（userAdress）或角色查询的条件
     */
    @ResponseBody
    @RequestMapping("/selectAllUser.json")
    public EasyuiData<SysUser> selectAllUser(Page page,String value, String name, HttpServletRequest servletRequest){
        EasyuiData<SysUser> easyuiData = new EasyuiData<SysUser>();
        List<SysUser> userlist = new ArrayList<SysUser>();
        int total = 0;
        //搜索框内容为空
        if(StringUtils.isEmpty(value)){
            total = userService.getUserTotle();
            userlist = userService.findAll(page);
        }else{
            SysUser sysUser = new SysUser();
            if("userName".equals(name)){
                sysUser.setUserName(value);
            }else if("userCode".equals(name)){
                sysUser.setUserCode(Long.parseLong(value));
            }else if("userAddress".equals(name)){
                sysUser.setUserAddress(CityUtil.CityARRAY[Integer.parseInt(value)]);
            }else if("userRoleIds".equals(name)){
                sysUser.setUserRoleIds(value);
            }
            total = userService.getUserTotleBy(sysUser);
            userlist = userService.findAllBy(page,sysUser);
        }
        easyuiData.setTotal(total);
        easyuiData.setRows(userlist);
        return easyuiData;
    }

    @ResponseBody
    @RequestMapping(value = "/insertUser.json")
    public MsgToPage<SysUser> insertUser(SysUser user) {
        MsgToPage<SysUser> msgToPage = new MsgToPage<SysUser>();
        if(checkName(user)) {
            //id code CreateTime 由后台生成
            user.setUserCode(12345L);
            //创建生成时间
            user.setUserCreateTime(new Date());
            //将前台传来的城市id转化为城市名
            CityUtil.SetCityIdToString(user);
            //得到影响的行数，来判断是否插入成功
            if((userService.createUser(user)) == 1) {
                msgToPage.setCode(RespondCode.SUCCESS);
                msgToPage.setMsg("添加"+user.getUserName()+"用户成功！"+"用户编码是"+user.getUserCode()+"请牢记！");
            }else {
                msgToPage.setCode(RespondCode.FAIL);
                msgToPage.setMsg("添加"+user.getUserName()+"用户失败！");
            }
        }else {
            msgToPage.setCode(RespondCode.RENAME);
            msgToPage.setMsg("'"+user.getUserName()+"'此用户名已存在！");
        }
        return msgToPage;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser.json")
    public MsgToPage<SysUser> updateUser(SysUser user) {
        MsgToPage<SysUser> msgToPage = new MsgToPage<SysUser>();
        //将前台传来的城市id转化为城市名
        if(isNumeric(user.getUserAddress())){
            CityUtil.SetCityIdToString(user);
        }
        int count = userService.updateUser(user);
        if(count == 1) {
            msgToPage.setCode(RespondCode.SUCCESS);
            msgToPage.setMsg("编辑"+user.getUserName()+"用户成功！");
        }else {
            msgToPage.setCode(RespondCode.FAIL);
            msgToPage.setMsg("编辑"+user.getUserName()+"用户失败！");
        }
        return msgToPage;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteOneUser.json")
    public MsgToPage<SysUser> deleteOneUser(SysUser user) {
        System.out.println(user);
        MsgToPage<SysUser> msgToPage = new MsgToPage<SysUser>();
        int count = userService.deleteUser(user.getUserId());
        if(count == 1) {
            msgToPage.setCode(RespondCode.SUCCESS);
            msgToPage.setMsg("删除用户成功！");
        }else {
            msgToPage.setCode(RespondCode.FAIL);
            msgToPage.setMsg("删除用户失败！");
        }
        return msgToPage;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteListUser.json")
    public MsgToPage<SysUser> deleteListUser(String[] ids) {
        MsgToPage<SysUser> msgToPage = new MsgToPage<SysUser>();
        int count = userService.deleteListUser(ids);
        if(count > 0) {
            msgToPage.setCode(200);
            msgToPage.setMsg("删除"+count+"条用户成功！");
        }else {
            msgToPage.setCode(404);
            msgToPage.setMsg("删除用户失败！");
        }
        return msgToPage;
    }

    /*
    * @Description:  获取城市列表 ，用于角色添加
    */
    @ResponseBody
    @RequestMapping("/getCity.json")
    public List<City> getCity(){
        return CityUtil.getCityList();
    }

    @ResponseBody
    @RequestMapping("/getRole.json")
    public List<SysRole> getRole(){
        return roleService.findAll();
    }

    /*

     * @Description:  获取角色列表，用于角色添加
     */
    @ResponseBody
    @RequestMapping("/getRoleType.json")
    public List<SysRole> getRoleType(HttpServletRequest servletRequest){
        //获取当前角色的权限级别
        SysUser user = (SysUser) servletRequest.getSession().getAttribute(Constants.CURRENT_USER);
        String ids = user.getUserRoleIds();
        String id[] = ids.split(",");
        Long roleId = Long.parseLong(id[0]);
        //获取所有角色
        List<SysRole> list = new ArrayList<SysRole>();
        //去除比当前用户角色权限高的角色
        for (SysRole item : roleService.findAll()) {
            if(item.getRoleLevel() < roleService.findOne(roleId).getRoleLevel()){
                list.add(item);
            }
        }
        return list;
    }

    /*判断字符串中是否包含数字*/
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    //检查用户名是否已经存在
    public Boolean checkName(SysUser user){
        return userService.checkName(user);
    }
}
