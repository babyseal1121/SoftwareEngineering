package com.example.studyx.service;

import com.example.studyx.dao.GradeDAO;
import com.example.studyx.dao.GradeSetDAO;
import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.Grade;
import com.example.studyx.pojo.GradeSet;
import com.example.studyx.pojo.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

//对数据库操作的进一步封装
@Service
public class GradeService {
    @Autowired
    GradeSetDAO gradesetDAO;

    @Autowired
    GradeDAO gradeDAO;

    public List<GradeSet> list() {
        //Sort sort = new Sort(Sort.Direction.DESC, "isbn");
        return gradesetDAO.findAll(Sort.by(Sort.Direction.DESC, "classno"));
    }

    public List<Grade> listofgrade() {
        //Sort sort = new Sort(Sort.Direction.DESC, "isbn");
        return gradeDAO.findAll(Sort.by(Sort.Direction.DESC, "userid"));
    }
}
