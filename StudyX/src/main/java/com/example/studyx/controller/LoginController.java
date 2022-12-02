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
    @GetMapping("/api/test1")
    public Result test(@RequestParam String no){
        User user = userService.getByMail(no);
        //Exercise exercise=exerciseDAO.findByExerciseno(no);
        if(null!=user)
            return new Result(200,"搜索成功",user);
        else
            return new Result(400,"搜索失败",no);

    }

    @CrossOrigin
    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String mail = requestUser.getMail();//获取用户输入的mail
        mail = HtmlUtils.htmlEscape(mail);
        //先得到salt加密的值
        User user = userService.getByMail(mail);//根据mail从数据库中查找对应的user
        if (null == user) {//查不到
            return ResultFactory.buildFailResult("账号不存在");
        }
        else {
            //看密码是否对应
            if(user.getPassword().equals(requestUser.getPassword()))
                return new Result(200,"搜索成功",user);
            else{
                return new Result(400,"密码错误",mail);
            }
        }
    }

}
