package com.cug.lab.controller;


import com.cug.lab.Constants;
import com.cug.lab.model.SysResource;
import com.cug.lab.model.SysRole;
import com.cug.lab.model.SysUser;
import com.cug.lab.service.ResourceService;
import com.cug.lab.service.RoleService;
import com.cug.lab.service.UserService;
import com.cug.lab.utils.PasswordHelper;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class LoginController {


    @Resource
    private  UserService userService;

    @Resource
    private  RoleService roleService;

    @Resource
    private ResourceService resourceService;

    @RequestMapping("/login.page")
    public String LoginPage(){
        return "login";
    }
    
    @RequestMapping("/logout.page")
    public String Loginout(){
        return "login";
    }
    
    @RequestMapping("/login.json")
    public String Login(HttpServletRequest servletRequest, Model model,SysUser sysUser){
    	String error = null;
        SysUser user = userService.findByUsername(sysUser.getUserName());
        if(user != null) {
        	if((PasswordHelper.getMD5String(sysUser).getUserPsd()).equals(user.getUserPsd())) {
        		servletRequest.getSession().setAttribute(Constants.CURRENT_USER, user);
        		
                Set<String> set = userService.findRoles(user.getUserName());
                List<SysRole> roleList = roleService.findListByName(set);
                Set<String> permissions = userService.findPermissions(user.getUserName());
                System.out.println(permissions);
              
               
                Map<String , List<SysResource>>  menus = resourceService.findMenus(permissions);
                model.addAttribute("roles", roleList);
                model.addAttribute("menus", menus);
        		return "homepage";
        	}else {
        		error = "密码不正确！";
        		model.addAttribute("error", error);
        		return "login";
        	}
        }else {
        	error = "账号不存在！";
    		model.addAttribute("error", error);
    		return "login";
        }
    }


    @RequestMapping("/welcome.page")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/unauthorized.page")
    public String unauthorized(){
        return "unauthorized";
    }


    @RequestMapping("/register.page")
    public String register(){
        return "register";
    }

}
