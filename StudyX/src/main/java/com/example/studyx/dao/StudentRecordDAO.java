package com.example.studyx.dao;

import com.example.studyx.pojo.StudentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRecordDAO extends JpaRepository<StudentRecord,Integer>{
    StudentRecord getByUserid(Integer userid);
    //User findById(Integer id);
    /*User findByMail(String mail);

    User findByUsername(String username);

    User getByUsernameAndPassword(String username,String password);
    User getByMailAndPassword(String mail,String password);

    //根据级别获取人员
    List<User> findByLevel(String level);*/
}
