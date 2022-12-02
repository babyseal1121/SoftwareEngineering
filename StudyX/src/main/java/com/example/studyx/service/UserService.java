package com.example.studyx.service;

import com.example.studyx.dao.UserDAO;
import com.example.studyx.pojo.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

//对数据库操作的进一步封装
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public User getByMail(String mail) {
        return userDAO.findByMail(mail);
    }
}
