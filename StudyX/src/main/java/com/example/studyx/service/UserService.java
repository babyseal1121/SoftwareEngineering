package com.example.studyx.service;

import com.example.studyx.dao.UserDAO;
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
public class UserService {
    @Autowired
    UserDAO userDAO;

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
    public List<User> list() {
        //Sort sort = new Sort(Sort.Direction.DESC, "isbn");
        return userDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }
    public User getByMail(String mail) {
        return userDAO.findByMail(mail);
    }

    public User getById(Integer id) {
        return userDAO.getById(id);
    }

    public boolean isExist(String mail) {
        User user = getByMail(mail);
        return null!=user;
    }

    public User get(String mail, String password){
        return userDAO.getByMailAndPassword(mail, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }

    public int register(User user) {
        String mail = user.getMail();
        String username = user.getUsername();
        String password = user.getPassword();

        mail = HtmlUtils.htmlEscape(mail);
        user.setMail(mail);
        user.setUsername(username);

        if (mail.equals("") || password.equals("")||username.equals("")) {
            return 0;
        }
        boolean exist = isExist(mail);

        if (!exist) {
            return 1;
        }
        // 默认生成 16 位盐，干扰数据
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        user.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);
        user.setLevel("未激活");
        userDAO.save(user);
        return 2;
    }

    public int findpassword(User user) {
        String mail = user.getMail();
        String password = user.getPassword();

        mail = HtmlUtils.htmlEscape(mail);
        user.setMail(mail);

        if (mail.equals("") || password.equals("")) {
            return 0;
        }
        boolean exist = isExist(mail);

        if (!exist) {
            return 2;
        }
        User user1=getByMail(mail);//在数据库中找到对应的user1

        user.setLevel(user1.getLevel());
        user.setStatus(user1.getStatus());
        user.setId(user1.getId());//把user的id设置成user1的id//不知所云
        // 默认生成 16 位盐，干扰数据
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        user.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        user.setPassword(encodedPassword);
        user.setUsername(user1.getUsername());
        userDAO.save(user);
        return 1;
    }
}
