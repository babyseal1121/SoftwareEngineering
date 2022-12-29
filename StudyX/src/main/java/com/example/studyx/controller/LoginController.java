package com.example.studyx.controller;


import com.example.studyx.pojo.Exercise;
import com.example.studyx.pojo.User;
import com.example.studyx.result.Result;
import com.example.studyx.result.ResultFactory;
import com.example.studyx.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

//有几点需要注意，postmapping对应前端 .post
//requestBody 参数后面跟着相关的类


@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/getuserid")
    public Result getUserid(@RequestBody User user1){
        System.out.println(user1.getMail());
        User user = userService.getByMail(user1.getMail());
        if(null!=user)
            return new Result(200,"搜索成功",user);
        else
            return new Result(400,"搜索失败",user1.getMail());
    }

    @CrossOrigin
    @PostMapping(value = "/api/level")
    public Result getLevel(@RequestBody User user1){
        //System.out.println(user1.getMail());
        User user = userService.getByMail(user1.getMail());
        if(null!=user)
            return new Result(200,"搜索成功",user.getLevel());
        else
            return new Result(400,"搜索失败",user1.getMail());
    }

    @CrossOrigin
    @PostMapping(value = "/api/menulist")
    public Result getMenuList(@RequestBody User user1){
        //System.out.println(user1.getMail());
        User user = userService.getByMail(user1.getMail());
        //Objest Menu
        if(null!=user)
            return new Result(200,"搜索成功",user.getLevel());
        else
            return new Result(400,"搜索失败",user1.getMail());
    }

    @CrossOrigin
    @GetMapping("/api/test1")
    public Result test(@RequestParam String no){
        User user = userService.getByMail(no);
        //Exercise exercise=exerciseDAO.findByExerciseno(no);
        if(null!=user)
            return new Result(200,"搜索成功",user);
        else
            return new Result(400,"搜索失败",no);

    }

    //注销登录
    @CrossOrigin
    @RequestMapping("/api/logout")
    public void logout(HttpSession session) {
        System.out.println("logout");
        //session失效
        if (session.getAttribute("user") != null)
            session.removeAttribute("user");
    }
    @CrossOrigin
    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {

        String mail = requestUser.getMail();
        mail = HtmlUtils.htmlEscape(mail);
        //先对比权限

        //先得到salt加密的值
        User user = userService.getByMail(mail);

        if (null == user) {
            return ResultFactory.buildFailResult("账号不存在");
        }
        String salt = user.getSalt();

        //加密密码，和原来的做对比
        String password = new SimpleHash("md5", requestUser.getPassword(), salt, 2).toString();
        ;
        System.out.println(password);
        user = userService.get(mail, password);
        if (null == user) {
            return ResultFactory.buildFailResult("账号不存在");
        } else {
            session.setAttribute("user", user);
            return ResultFactory.buildSuccessResult(user);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/findpassword")
    public Result findpassword(@RequestBody User user) {
        int status = userService.findpassword(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("邮箱或密码或用户名不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("找回密码成功");
            case 2:
                return ResultFactory.buildFailResult("该邮箱未注册，请先注册");
        }
        return ResultFactory.buildFailResult("未知错误");
    }

    @CrossOrigin
    @PostMapping(value = "/api/register")
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("邮箱或密码或用户名不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("注册成功");
            case 2:
                return ResultFactory.buildFailResult("该邮箱已注册");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
}
