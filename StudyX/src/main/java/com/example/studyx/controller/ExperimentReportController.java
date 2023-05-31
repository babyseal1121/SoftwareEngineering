package com.example.studyx.controller;

import com.example.studyx.domain.ExperimentProjectSimpleInfo;
import com.example.studyx.domain.ExperimentReportSimpleInfo;
import com.example.studyx.pojo.ExperimentProject;
import com.example.studyx.pojo.ExperimentReport;
import com.example.studyx.result.Result;
import com.example.studyx.service.ExperimentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//实验报告的控制器
@RestController
public class ExperimentReportController {

    @Autowired
    ExperimentReportService experimentReportService;


    // 提交实验报告文档
    @CrossOrigin
    @PostMapping(value = "/api/experiment/submitexperimentreportfile")
    public  Result submitExperimentReportFile(@RequestBody MultipartFile studyFile, @RequestParam int userId, @RequestParam int experimentNo, @RequestParam String fileName)
    {
        try {
            String filePath = experimentReportService.submitExperimentReportFile(studyFile, userId, experimentNo, fileName);
            return new Result(200,"success", filePath);
        }
        catch (Exception e){

            System.out.println(e.toString());
            return new Result(400,"failure", "提交实验报告文档失败");
        }
    }


    //提交实验报告
    @CrossOrigin
    @PostMapping(value = "/api/experiment/submitexperimentreport")
    public Result submitExperimentReport(@RequestBody ExperimentReport experimentReport){

        try {
            experimentReportService.submitExperimentReport(experimentReport);

            return new Result(200,"success", "提交实验报告成功");
        }
        catch (Exception e){

            return new Result(400,"failure", "提交实验报告失败");
        }

    }

    //批改实验报告
    @CrossOrigin
    @PostMapping(value = "/api/experiment/appraiseexperimentreport")
    public Result appraiseExperimentReport(@RequestParam int experimentReportNo, @RequestParam int experimentGrade, @RequestParam String experimentComments){

        try {
            experimentReportService.appraiseExperimentReport(experimentReportNo, experimentGrade, experimentComments);

            return new Result(200,"success", "批改实验报告成功");
        }
        catch (Exception e){

            return new Result(400,"failure", "批改实验报告失败");
        }
    }

    //获取实验报告的详细内容
    @CrossOrigin
    @GetMapping(value = "/api/experiment/getexperimentreport")
    public Result getExperimentReport(@RequestParam int experimentReportNo){

        try {
            ExperimentReport experimentReport =  experimentReportService.getExperimentReport(experimentReportNo);

            return new Result(200,"success", experimentReport);
        }
        catch (Exception e){

            return new Result(400,"failure", "获取实验报告细节失败");
        }
    }

    //获取学生本人的所有实验报告
    @CrossOrigin
    @GetMapping(value = "/api/experiment/getexperimentreportlist")
    public Result getExperimentReportList(@RequestParam int userId){

        try {
            List<ExperimentReportSimpleInfo> list = experimentReportService.getExperimentReportList(userId);

            return new Result(200,"success", list);
        }
        catch (Exception e){

            return new Result(400,"failure", "获取实验报告列表失败");
        }
    }

}
