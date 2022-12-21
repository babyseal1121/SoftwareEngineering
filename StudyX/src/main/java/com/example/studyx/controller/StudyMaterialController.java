package com.example.studyx.controller;


import com.example.studyx.pojo.StudyMaterial;
import com.example.studyx.result.Result;
import com.example.studyx.service.StudyMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class StudyMaterialController {

    @Autowired
    StudyMaterialService studyMaterialService;

    //上传文件学习资料
    @CrossOrigin
    @PostMapping(value = "/api/studymaterial/submitstudymaterial")
    public Result submitStudyMaterial(@RequestBody MultipartFile studyFile, @RequestParam String fileName, @RequestParam String pathName, @RequestParam String uploadTime){

        try {
            studyMaterialService.submitStudyMaterial(studyFile, fileName, pathName, uploadTime);

            return new Result(200,"success", "上传资料成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "上传资料失败");
        }
    }

    //获取资料
    @CrossOrigin
    @GetMapping(value = "/api/studymaterial/getstudymaterial")
    public Result getStudyMaterial(HttpServletResponse response, @RequestParam int materialNo){

        try {
            return studyMaterialService.getStudyMaterial(response,materialNo);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取资料失败");
        }
    }

    //删除文件
    @CrossOrigin
    @PostMapping(value = "/api/studymaterial/deletestudymaterial")
    public Result deleteStudyMaterial(@RequestParam int materialNo){

        try {
            String result = studyMaterialService.deleteStudyMaterial(materialNo);
            if(result.equals("删除文件成功"))
                return new Result(200,"success", "删除文件成功");
            else
                return new Result(400,"failure", result);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "删除资料失败");
        }
    }

    //改变文件存储位置
    @CrossOrigin
    @PostMapping(value = "/api/studymaterial/changestudymaterialpath")
    public Result changeStudyMaterialPath(@RequestParam int materialNo, @RequestParam String materialPath){

        try {
            String result = studyMaterialService.changeStudyMaterialPath(materialNo, materialPath);
            if(result.equals("转移文件成功"))
                return new Result(200,"success", "转移文件成功");
            else
                return new Result(400,"failure", result);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "转移文件失败");
        }
    }

    //获取文件信息列表
    @CrossOrigin
    @GetMapping(value = "/api/studymaterial/getstudymateriallist")
    public Result getStudyMaterialList(){

        try {
            List<StudyMaterial> list = studyMaterialService.getStudyMaterialList();

            return new Result(200,"success", list);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,"failure", "获取文件列表信息失败");
        }
    }

}
