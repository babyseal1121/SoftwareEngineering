package com.example.studyx.dao;

import com.example.studyx.pojo.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeDAO extends JpaRepository<Grade,Integer>{
    Grade getByUserid(Integer userid);
    //User findById(Integer id);
    /*User findByMail(String mail);

    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);
    User getByMailAndPassword(String mail,String password);

    //根据级别获取人员
    List<User> findByLevel(String level);*/
}
