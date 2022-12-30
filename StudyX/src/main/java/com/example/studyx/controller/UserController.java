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

import javax.servlet.http.HttpSession;

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

    @CrossOrigin
    @PostMapping("/api/delete")
    public Result delete(@RequestBody User user) throws Exception {
        //int id=remarkid;
        userService.deleteById(user.getId());
        System.out.println("删除用户"+user.getId());
        return new Result(200,"用户已删除",user.getId());

    }

    @CrossOrigin
    @PostMapping("/api/changelevel")
    public Result changelevel(@RequestBody User user) throws Exception {
        //int id=remarkid;
        //根据传递的信息找到对应用户
        User user1=userDAO.getById(user.getId());
        //修改用户权限
        user1.setLevel(user.getLevel());
        userDAO.save(user1);
        System.out.println("修改后的权限："+user1.getLevel());

        return new Result(200,"权限修改成功",user1);

    }

}
