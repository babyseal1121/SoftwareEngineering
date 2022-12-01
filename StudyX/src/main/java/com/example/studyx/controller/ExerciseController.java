package com.example.studyx.controller;

import com.example.studyx.dao.ExerciseDAO;
import com.example.studyx.pojo.Exercise;

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
public class ExerciseController {

    @Autowired
    ExerciseDAO exerciseDAO;

    @CrossOrigin
    @GetMapping("/api/test")
    public Result test(@RequestParam String no){
        Exercise exercise=exerciseDAO.findByExerciseno(no);
        return new Result(400,"搜索成功",exercise);

    }
}
