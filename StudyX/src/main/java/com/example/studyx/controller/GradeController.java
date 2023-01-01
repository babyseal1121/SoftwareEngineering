package com.example.studyx.controller;

import com.example.studyx.dao.GradeDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Grade;
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
    GradeService gradeService;

    @CrossOrigin
    @GetMapping("/api/gradeset")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(gradeService.list());
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
