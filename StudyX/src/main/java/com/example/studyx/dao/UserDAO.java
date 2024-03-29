package com.example.studyx.dao;

import com.example.studyx.pojo.Exercise;
import com.example.studyx.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer>{
    User getById(Integer id);
    //User findById(Integer id);
    User findByMail(String mail);

    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);
    User getByMailAndPassword(String mail,String password);

    //根据级别获取人员
    List<User> findByLevel(String level);
}
