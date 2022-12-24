package com.example.studyx.controller;


import com.example.studyx.domain.ExerciseInfo;
import com.example.studyx.domain.ExerciseResult;
import com.example.studyx.pojo.Exercise;
import com.example.studyx.pojo.ExerciseProblem;
import com.example.studyx.pojo.MemberInExercise;
import com.example.studyx.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.studyx.result.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    //发布测试
    @CrossOrigin
    @PostMapping(value = "/api/exercise/publishexercise")
    public Result publishExercise(@RequestBody ExerciseInfo exerciseInfo){

        try {
            exerciseService.publishExercise(exerciseInfo);

            return new Result(200,"success", "发布新测试成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "发布新测试失败");
        }
    }

    //删除测试
    @CrossOrigin
    @PostMapping(value = "/api/exercise/deleteexercise")
    public Result deleteExercise(@RequestParam int exerciseNo){

        try {
            exerciseService.deleteExercise(exerciseNo);

            return new Result(200,"success", "删除测试成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "删除测试失败");
        }
    }

    //获取测试一场测试的详细信息
    @CrossOrigin
    @GetMapping(value = "/api/exercise/getexercise")
    public Result getExercise(@RequestParam int exerciseNo){

        try {
            ExerciseInfo exerciseInfo =  exerciseService.getExercise(exerciseNo);

            return new Result(200,"success", exerciseInfo);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取测试详情失败");
        }
    }


    //获取一个人所有的测试的基础信息
    @CrossOrigin
    @GetMapping(value = "/api/exercise/getexerciselist")
    public Result getExerciseList(@RequestParam int userId){

        try {
            List<Exercise> list = exerciseService.getExerciseList(userId);

            return new Result(200,"success", list);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取测试列表失败");
        }
    }

    //提交测试结果
    @CrossOrigin
    @PostMapping(value = "/api/exercise/submitexercise")
    public Result submitExercise(@RequestBody MemberInExercise member){

        try {
            if(-1 == exerciseService.submitExercise(member))
                return new Result(400,"failure", "请勿重复提交");
            else
                return new Result(200,"success", "提交测试成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "提交测试失败");
        }
    }

    //获取一个人某场考试的详细情况
    @CrossOrigin
    @GetMapping(value = "/api/exercise/getexerciseresult")
    public Result getExerciseResult(@RequestParam int userId, @RequestParam int exerciseNo){

        try {
            ExerciseResult exerciseResult = exerciseService.getExerciseResult(userId, exerciseNo);

            return new Result(200,"success", exerciseResult);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取测试结果详情失败");
        }
    }

    //获取一场测试的题目
    @CrossOrigin
    @GetMapping(value = "/api/exercise/getexerciseproblemlist")
    public Result getExerciseProblemList(int exerciseNo){

        try {
            List<ExerciseProblem> list = exerciseService.getExerciseProblemList(exerciseNo);

            return new Result(200,"success", list);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取测试题目详情失败");
        }
    }

}
