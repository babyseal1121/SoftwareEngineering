package com.example.studyx.controller;

import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.User;
import com.example.studyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
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


    @CrossOrigin
    @PostMapping(value = "/api/user/getuserinfo")
    public Result getuserinfo(@RequestBody User user) {
        System.out.println("用户："+user.getId());
        User user1 = userDAO.getById(Integer.valueOf(user.getId()));
        return new Result(200,"权限修改成功",user1);

    }

    @CrossOrigin
    @PostMapping(value = "/api/user/editinfo")
    public Result edituserinfo(@RequestBody User user) {
       // Integer id = Integer.valueOf(user.getId());
        User usera = userDAO.getById(user.getId());
        if (user.getPassword() != "") {
            // 默认生成 16 位盐，干扰数据
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            usera.setSalt(salt);
            String encodedPassword = new SimpleHash("md5", user.getPassword(), salt, times).toString();
            usera.setPassword(encodedPassword);
        }
        else
            usera.setPassword(usera.getPassword());

        if (user.getAge() != "") {
            usera.setAge(user.getAge());
        }
        else
            usera.setAge(usera.getAge());
        if (user.getGender() != "") {
            usera.setGender(user.getGender());
        }
        else
            usera.setGender(usera.getGender());
        if (user.getInformation() != "") {
            usera.setInformation(user.getInformation());
        }
        else
            usera.setInformation(usera.getInformation());
        if (user.getPhone() != "") {
            usera.setPhone(user.getPhone());
        }
        else
            usera.setPhone(usera.getPhone());
        if (user.getSchool() != "") {
            usera.setSchool(user.getSchool());
        }
        else
            usera.setSchool(usera.getSchool());
        if (user.getPhoto() != "") {
            usera.setPhoto(user.getPhoto());
        }
        else
            usera.setPhoto(usera.getPhoto());
        userDAO.save(usera);
        System.out.println(usera.getGender());
        System.out.println(user.getGender());
        return new Result(200,"修改成功",usera);
    }

}
