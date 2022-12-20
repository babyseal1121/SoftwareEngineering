package com.example.studyx.controller;

import com.example.studyx.pojo.ExerciseProblem;
import com.example.studyx.result.Result;
import com.example.studyx.service.ExerciseProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseProblemController {

    @Autowired
    ExerciseProblemService exerciseProblemService;

    //创建新的测试用题目的接口
    @CrossOrigin
    @PostMapping(value = "/api/exercise/createexerciseproblem")
    public Result createExerciseProblem(@RequestBody ExerciseProblem exerciseProblem){

        try {
            exerciseProblemService.createExerciseProblem(exerciseProblem);

            return new Result(200,"success", "加入新题目成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "加入新题目失败");
        }
    }

    //删除实验题目
    @CrossOrigin
    @PostMapping(value = "/api/exercise/deleteexerciseproblem")
    public Result deleteExerciseProblem(@RequestParam int problemNo){

        try {
            exerciseProblemService.deleteExerciseProblem(problemNo);

            return new Result(200,"success", "删除题目成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "删除题目失败");
        }
    }

    //获取一个实验项目的题目列表
    @CrossOrigin
    @PostMapping(value = "/api/exercise/getexperimentproblemlist")
    public Result getExperimentProblemList(@RequestParam int experimentNo){

        try {
            List<ExerciseProblem> list = exerciseProblemService.getExperimentProblemList(experimentNo);

            return new Result(200,"success", list);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取题目列表失败");
        }
    }

    //获取一个实验题目
    @CrossOrigin
    @PostMapping(value = "/api/exercise/getexperimentproblem")
    public Result getExperimentProblem(@RequestParam int problemNo){

        try {
            ExerciseProblem exerciseProblem = exerciseProblemService.getExperimentProblem(problemNo);

            return new Result(200,"success", exerciseProblem);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取题目失败");
        }
    }
}
