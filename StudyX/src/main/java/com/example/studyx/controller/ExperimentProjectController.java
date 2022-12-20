package com.example.studyx.controller;

import com.example.studyx.domain.ExperimentProjectSimpleInfo;
import com.example.studyx.pojo.ExperimentProject;
import com.example.studyx.result.Result;
import com.example.studyx.service.ExperimentProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExperimentProjectController {

    @Autowired
    ExperimentProjectService experimentProjectService;

    //增加实验项目的接口
    @CrossOrigin
    @PostMapping(value = "/api/experiment/createexperiment")
    public Result CreateExperiment(@RequestBody ExperimentProject experimentProject){

        try {
            experimentProjectService.addExperimentProject(experimentProject);

            return new Result(200,"success", "加入新实验项目成功");
        }
        catch (Exception e){

            return new Result(400,"failure", "加入新实验项目失败");
        }


    }

    //删除实验项目的接口
    @CrossOrigin
    @PostMapping(value = "/api/experiment/deleteaexperiment")
    public Result DeleteExperiment(@RequestParam int experimentNo){
        try {
            experimentProjectService.deleteExperimentProject(experimentNo);

            return new Result(200,"success", "删除实验项目成功");
        }
        catch (Exception e){

            return new Result(400,"failure", "删除实验项目失败");
        }
    }

    //获取单个实验的详细内容
    @CrossOrigin
    @PostMapping(value = "/api/experiment/getexperiment")
    public Result GetExperiment(@RequestParam int experimentNo){

        try {
            ExperimentProject experimentProject =  experimentProjectService.getExperimentProjectById(experimentNo);

            if(null != experimentProject)
                return new Result(200,"success", experimentProject);
            else
                return new Result(400,"failure", "未查询到该实验项目");
        }
        catch (Exception e){

            return new Result(400,"failure", "查询实验项目失败");
        }
    }

    //获取所有实验项目的简易信息列表
    @CrossOrigin
    @PostMapping(value = "/api/experiment/getexperimentlist")
    public Result GetExperimentList(){

        try {
            List<ExperimentProjectSimpleInfo> list = experimentProjectService.getAllExperimentSimpleInfo();

            return new Result(200,"success", list);

        }
        catch (Exception e){

            return new Result(400,"failure", "查询实验项目列表失败");
        }
    }
}
