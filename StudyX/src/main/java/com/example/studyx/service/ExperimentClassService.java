package com.example.studyx.service;

import com.example.studyx.dao.ExperimentClassDAO;
import com.example.studyx.dao.ExperimentInClassDAO;
import com.example.studyx.dao.MemberInClassDAO;
import com.example.studyx.domain.ExperimentClassInfo;
import com.example.studyx.pojo.ExperimentClass;
import com.example.studyx.pojo.ExperimentInClass;
import com.example.studyx.pojo.MemberInClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExperimentClassService {

    @Autowired
    ExperimentClassDAO experimentClassDAO;
    @Autowired
    MemberInClassDAO memberInClassDAO;
    @Autowired
    ExperimentInClassDAO experimentInClassDAO;

    //创建新的班级
    public void createExperimentClass(ExperimentClassInfo experimentClassInfo){

        //生成一个随机码便于找到自增后的课程
        int randKey = new Random().nextInt(10000);

        //存储班级的信息
        ExperimentClass experimentClass = new ExperimentClass(experimentClassInfo, randKey);
        //获取班级人员的信息
        List<Integer> userList = experimentClassInfo.getUsersNo();
        int userListSize = userList.size();
        //获取班级实验项目的信息
        List<Integer>experimentList = experimentClassInfo.getExperimentsNo();
        int experimentListSize = experimentList.size();

        //先存储班级基础信息
        experimentClassDAO.save(experimentClass);
        //记录自增序号
        experimentClass = experimentClassDAO.findByRandkey(randKey);
        int experimentClassNo = experimentClass.getExperimentclassno();

        //存储参与班级人员信息
        for(int i = 0; i < userListSize; i++){

            MemberInClass member = new MemberInClass(userList.get(i), experimentClassNo);
            memberInClassDAO.save(member);
        }
        //存储班级内实验项目的信息
        for(int i = 0; i < experimentListSize; i++){

            ExperimentInClass experimentInClass = new ExperimentInClass(experimentList.get(i), experimentClassNo);
            experimentInClassDAO.save(experimentInClass);
        }

        //清空随机码
        experimentClass.setRandkey(-1);
        experimentClassDAO.save(experimentClass);
    }

    //删除班级
    public void deleteExperimentClass(int experimentClassNo){

        //删除班级内成员
        memberInClassDAO.deleteByExperimentclassno(experimentClassNo);
        //删除班级内实验项目
        experimentInClassDAO.deleteByExperimentclassno(experimentClassNo);
        //删除班级基础信息
        experimentClassDAO.deleteById(experimentClassNo);
    }

    //获取班级内的详细信息
    public ExperimentClassInfo getExperimentClass(int experimentClassNo){

        //查找课程基础信息
        ExperimentClass experimentClass = experimentClassDAO.findByExperimentclassno(experimentClassNo);
        //查找有关人员的信息
        List<MemberInClass> userList = memberInClassDAO.findByExperimentclassno(experimentClassNo);
        int userListSize = userList.size();
        //查找有关实验项目的信息
        List<ExperimentInClass> experimentList = experimentInClassDAO.findByExperimentclassno(experimentClassNo);
        int experimentListSize = experimentList.size();
        //存储人员Id
        List<Integer> userIdList = new ArrayList<>();
        //存储实验项目编号
        List<Integer> experimentIdList = new ArrayList<>();

        //提取人员的Id
        for(int i = 0; i < userListSize; i++){

            userIdList.add((userList.get(i)).getUserid());
        }
        //提取项目编号
        for(int i = 0; i < experimentListSize; i++){

            experimentIdList.add((experimentList.get(i)).getExperimentno());
        }

        return new ExperimentClassInfo(experimentClass,userIdList, experimentIdList);

    }

    //查询所有班级信息
    public List<ExperimentClass> getExperimentClassList(){

        return experimentClassDAO.findAll();
    }

    //删除班级内实验报告
    public void deleteClassExperiment(int experimentNo, int experimentClassNo){

        experimentInClassDAO.deleteByExperimentclassnoAndExperimentno(experimentClassNo,experimentNo);
    }

    //向班级内添加实验报告
    public void publishExperiment(int experimentNo, int experimentClassNo){

        experimentInClassDAO.save(new ExperimentInClass(experimentNo, experimentClassNo));
    }

    //向班级内添加人员
    public void addClassMember(int userId, int experimentClassNo){

        memberInClassDAO.save(new MemberInClass(userId, experimentClassNo));
    }

    //向班级内删除人员
    public void deleteClassMember(int userId, int experimentClassNo){

        memberInClassDAO.deleteByExperimentclassnoAndUserid(experimentClassNo, userId);
    }

    //查找人员所在班级
    public int findExperimentClass(int userId){

        MemberInClass member = memberInClassDAO.findByUserid(userId);

        return member.getExperimentclassno();
    }
}
