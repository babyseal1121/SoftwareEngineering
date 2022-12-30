package com.example.studyx.controller;

import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Exercise;
import com.example.studyx.pojo.User;
import com.example.studyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.studyx.result.Result;
import com.example.studyx.result.ResultFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/api/user/login")
    public Result test(@RequestParam int id){
        User user=userDAO.getById(id);
        if(null!=user)
            return new Result(200,"搜索成功",user);
        else
            return new Result(400,"搜索失败",id);

    }

    @CrossOrigin
    @GetMapping("/api/userlist")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }
}
