package com.etc.controller;


import com.etc.entity.User;
import com.etc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    //实现对用户的增删查改操作

    @Resource
    private UserService userService;


    /*//查询所有用户
    @RequestMapping("/queryall")
    public ModelAndView queryall(){
       ModelAndView mv=new ModelAndView("userallquery");
       mv.addObject("list",userService.find());
       return mv;
    }*/
    //新加登录方法
    @RequestMapping("/userlogin")
    public ModelAndView userlogin(String uname, String password, HttpServletResponse response, HttpSession session){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/userallquery"));
       if(uname==null && password==null){
           //验证
           mv.setViewName("login");
           return  mv;
       }
       User u=userService.loginquery(uname,password);
       if (u!=null){
           //登录成功
           session.setAttribute("user",u);
       }
       else {
           mv.setViewName("login");
           mv.addObject("error","用户名密码错误");
       }
        return mv;
    }


    //分页查询所有用户
    @RequestMapping("/userallquery")
    public ModelAndView query(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        ModelAndView mv = new ModelAndView("userallquery");
        mv.addObject("p",userService.findPage(pageNum,pageSize));
        return mv;
    }



    //新增用户-转发到useradd.html页面去填写
    @RequestMapping("/usertoadd")
   public ModelAndView toadd(){
        ModelAndView mv=new ModelAndView("useradd");
        return mv;
    }

    //新增用户并且跳到查询页面
    @RequestMapping("/useradd")
    public ModelAndView add(User u){
        ModelAndView mv=new ModelAndView(new RedirectView("userget/"+u.getUid())); //重定向到另一个url映射
        userService.userinsert(u);
        mv.addObject("msg","新加成功");
        return mv;
    }

    //获取通过uid得到的用户的信息
    @RequestMapping("/userget/{uid}")
    public ModelAndView get(@PathVariable Integer uid){ //PathVariable占位符
        ModelAndView mv=new ModelAndView("userget");
        mv.addObject("u",userService.get(uid));
        return mv;
    }

    //通过uid来修改信息
    @RequestMapping("/usermod")
    public ModelAndView mod(User u){
        ModelAndView mv=new ModelAndView(new InternalResourceView("userget/"+u.getUid()));//转发到另外一个url映射
        userService.mod(u);
        mv.addObject("msg","修改成功");
        return mv;
    }

    //通过uid来删除
    @RequestMapping("/userdel/{uid}")
    public ModelAndView del(@PathVariable Integer uid){
        ModelAndView mv=new ModelAndView(new InternalResourceView("/queryall")); //转发到另一个url映射
       // claDao.del(cid);
        userService.del(uid);
        mv.addObject("msg","删除成功");
        return mv;
    }

}
