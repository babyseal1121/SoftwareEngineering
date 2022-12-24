package com.example.studyx.domain;

import com.example.studyx.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserSimpleInfo {

    //人员Id
    Integer userid;
    //人员等级
    String level;
    //人员状态
    String status;
    //人员名称
    String username;

    //使用User的信息构造简单的User的信息
    public UserSimpleInfo(User user) {

        userid = user.getId();
        level = user.getLevel();
        status = user.getStatus();
        username = user.getUsername();
    }
}
