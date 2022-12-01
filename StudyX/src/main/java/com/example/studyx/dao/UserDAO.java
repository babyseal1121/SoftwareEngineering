package com.example.studyx.dao;

import com.example.studyx.pojo.Exercise;
import com.example.studyx.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserDAO extends JpaRepository<User,Integer>{
    User findByUserid(Integer id);
}
