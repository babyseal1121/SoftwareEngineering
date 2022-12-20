package com.example.studyx.controller;

import com.example.studyx.domain.ExperimentClassInfo;
import com.example.studyx.domain.ExperimentReportSimpleInfo;
import com.example.studyx.pojo.ExperimentClass;
import com.example.studyx.pojo.ExperimentReport;
import com.example.studyx.result.Result;
import com.example.studyx.service.ExperimentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExperimentClassController {

    @Autowired
    ExperimentClassService experimentClassService;

    //创建新的班级
    @CrossOrigin
    @PostMapping(value = "/api/class/createexperimentclass")
    public Result createExperimentClass(@RequestBody ExperimentClassInfo experimentClassInfo){

        try {
            experimentClassService.createExperimentClass(experimentClassInfo);

            return new Result(200,"success", "创建班级成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "创建班级失败");
        }
    }

    //删除班级
    @CrossOrigin
    @PostMapping(value = "/api/class/deleteexperimentclass")
    public Result deleteExperimentClass(@RequestParam int experimentClassNo){

        try {
            experimentClassService.deleteExperimentClass(experimentClassNo);

            return new Result(200,"success", "删除班级成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "删除班级失败");
        }
    }

    //获取班级详细内容
    @CrossOrigin
    @GetMapping(value = "/api/class/getexperimentclass")
    public Result getExperimentClass(@RequestParam int experimentClassNo){

        try {
            ExperimentClassInfo experimentClassInfo = experimentClassService.getExperimentClass(experimentClassNo);

            return new Result(200,"success", experimentClassInfo);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取班级详细信息失败");
        }
    }

    //获取班级列表
    @CrossOrigin
    @GetMapping(value = "/api/class/getexperimentclasslist")
    public Result getExperimentClassList(){

        try {
            List<ExperimentClass> list = experimentClassService.getExperimentClassList();

            return new Result(200,"success", list);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取班级列表信息失败");
        }
    }

    //删除班级内的实验项目
    @CrossOrigin
    @PostMapping(value = "/api/class/deleteclassexperiment")
    public Result deleteClassExperiment(@RequestParam int experimentNo, @RequestParam int experimentClassNo){

        try {
            experimentClassService.deleteClassExperiment(experimentNo, experimentClassNo);

            return new Result(200,"success", "删除班级内实验报告成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "删除班级内实验报告失败");
        }
    }

    //向班级内添加项目
    @CrossOrigin
    @PostMapping(value = "/api/experiment/publishexperiment")
    public Result publishExperiment(@RequestParam int experimentNo, @RequestParam int experimentClassNo){

        try {
            experimentClassService.publishExperiment(experimentNo, experimentClassNo);

            return new Result(200,"success", "向班级内添加报告成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "向班级内添加报告失败");
        }
    }

    //向班级内添加人员
    @CrossOrigin
    @PostMapping(value = "/api/class/addclassmember")
    public Result addClassMember(@RequestParam int userId, @RequestParam int experimentClassNo){

        try {
            experimentClassService.addClassMember(userId, experimentClassNo);

            return new Result(200,"success", "向班级内添加人员成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "向班级内添加人员失败");
        }
    }

    //在班级内删除人员
    @CrossOrigin
    @PostMapping(value = "/api/class/deleteclassmember")
    public Result deleteClassMember(@RequestParam int userId, @RequestParam int experimentClassNo){

        try {
            experimentClassService.deleteClassMember(userId, experimentClassNo);

            return new Result(200,"success", "班级内删除人员成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "班级内删除人员失败");
        }
    }

    //查找人员所在班级
    @CrossOrigin
    @GetMapping(value = "/api/class/findexperimentclass")
    public Result findExperimentClass(@RequestParam int userId){

        try {
            int experimentClassNo = experimentClassService.findExperimentClass(userId);

            return new Result(200,"success", experimentClassNo);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "所在班级查询失败");
        }
    }

    //教师获取自己班级内的所有实验报告
    @CrossOrigin
    @GetMapping(value = "/api/experiment/getclassexperimentreportlist")
    public Result getClassExperimentReportList(@RequestParam int experimentClassNo){

        try {
            List<ExperimentReportSimpleInfo> list = experimentClassService.getClassExperimentReportList(experimentClassNo);

            return new Result(200,"success", list);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "所在班级所有报告查询失败");
        }
    }

}
