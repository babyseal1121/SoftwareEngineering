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
}
