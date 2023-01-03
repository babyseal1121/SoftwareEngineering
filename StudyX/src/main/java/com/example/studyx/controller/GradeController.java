package com.example.studyx.controller;

import com.example.studyx.dao.GradeDAO;
import com.example.studyx.dao.StudentRecordDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Grade;
import com.example.studyx.pojo.StudentRecord;
import com.example.studyx.pojo.GradeSet;
import com.example.studyx.dao.GradeSetDAO;
import com.example.studyx.pojo.User;
import com.example.studyx.service.GradeService;
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
public class GradeController {

    @Autowired
    GradeSetDAO gradesetDAO;
    @Autowired
    GradeDAO gradeDAO;

    @Autowired
    GradeService gradeService;

    @Autowired
    StudentRecordDAO studentrecordDAO;
    //增加考勤次数
    @CrossOrigin
    @PostMapping("/api/addattendance")
    public Result addattendance(@RequestBody Grade grade) {
        //获取权重集合
        Grade a = gradeDAO.getByUserid(grade.getUserid());
        //计算考勤分数
        a.setAttendancenum(a.getAttendancenum()+1.0);
        gradeDAO.save(a);
        System.out.println(a.getAttendancenum());
        //计算实验分数
        return ResultFactory.buildSuccessResult(a);
    }
    //获取考勤次数
    @CrossOrigin
    @PostMapping("/api/attendancenum")
    public Result attendancenum(@RequestBody StudentRecord studentrecord) {
        //获取权重集合
        StudentRecord a = studentrecordDAO.getByUserid(studentrecord.getUserid());
        //计算考勤分数
        //计算实验分数
        return ResultFactory.buildSuccessResult(a);
    }



    @CrossOrigin
    @GetMapping("/api/gradeset")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(gradeService.list());
    }

    @CrossOrigin
    @GetMapping("/api/gradelist")
    public Result listGrades() {
        return ResultFactory.buildSuccessResult(gradeService.listofgrade());
    }

    //成绩计算
    @CrossOrigin
    @PostMapping("/api/calculate")
    public Result CalculateGrades(@RequestBody GradeSet gradeset) {
        //获取权重集合
        GradeSet gradeseta = gradesetDAO.getByClassno(gradeset.getClassno());
        double attweight=gradeseta.getAttandanceweight();
        int attnum=gradeseta.getAttandancenum();
        //返回对应班级中的所有学生的成绩表
        List<Grade> gradelist=gradeService.StudentofClass(gradeseta.getClassno());
        //计算考勤分数
        //拿其中一个孩子举例：
        //考勤分数=100*考勤权重*（学生的考勤次数/考勤次数）
       /* gradelist.forEach(p -> p.setAttandancegrade(100*attweight*(p.getAttendancenum()/attnum)));
        gradelist.forEach(p->System.out.println("考勤："+p.getAttandancegrade()));*/
        for(int i=0;i<gradelist.size();i++){
            Grade test=gradelist.get(i);
            double g1=100*attweight*(test.getAttendancenum()/attnum);
            test.setAttandancegrade(g1);
            test.setTotalgrade(test.getTotalgrade()+g1);
            System.out.println(100*attweight*(test.getAttendancenum()/attnum));
            gradeDAO.save(test);
        }
        System.out.println("考勤权重："+attweight);


        //计算实验分数
        return ResultFactory.buildSuccessResult(gradelist);
    }

    @CrossOrigin
    @PostMapping(value = "/api/changegradeset")
    public Result changegradeset(@RequestBody GradeSet gradeset) {
        // Integer id = Integer.valueOf(user.getId());
        GradeSet gradeseta = gradesetDAO.getByClassno(gradeset.getClassno());

        System.out.println(gradeseta.getAttandancenum());

        if (gradeset.getAttandancenum() != null) {
            gradeseta.setAttandancenum(gradeset.getAttandancenum());
        }
        else
            gradeseta.setAttandancenum(gradeseta.getAttandancenum());

        if (gradeset.getAttandanceweight() != null) {
            gradeseta.setAttandanceweight(gradeset.getAttandanceweight());
        }
        else
            gradeseta.setAttandanceweight(gradeseta.getAttandanceweight());

        if (gradeset.getExperimentnum() != null) {
            gradeseta.setExperimentnum(gradeset.getExperimentnum());
        }
        else
            gradeseta.setExperimentnum(gradeseta.getExperimentnum());

        if (gradeset.getExperimentweight() != null) {
            gradeseta.setExperimentweight(gradeset.getExperimentweight());
        }
        else
            gradeseta.setExperimentweight(gradeseta.getExperimentweight());

        gradesetDAO.save(gradeseta);
        System.out.println(gradeset.getAttandancenum());
        return new Result(200,"修改成功",gradeseta);
    }
}