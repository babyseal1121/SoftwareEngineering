package com.example.studyx.service;

import com.example.studyx.dao.ExperimentProjectDAO;
import com.example.studyx.domain.ExperimentProjectSimpleInfo;
import com.example.studyx.pojo.ExperimentProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//进一步封装数据库操作
@Service
public class ExperimentProjectService {

    @Autowired
    ExperimentProjectDAO experimentProjectDAO;

    //新增实验项目
    public void addExperimentProject(ExperimentProject experimentProject){

        experimentProjectDAO.save(experimentProject);
    }

    //删除实验项目
    public void deleteExperimentProject(int experimentNo){

        experimentProjectDAO.deleteById(experimentNo);
    }

    //根据id获取实验项目细节
    public ExperimentProject getExperimentProjectById(int experimentNo){

        return experimentProjectDAO.findById(experimentNo);
    }

    //获取所有实验的列表
    public List<ExperimentProjectSimpleInfo> getAllExperimentSimpleInfo(){

        //获取所有实验项目
        List<ExperimentProject> list = experimentProjectDAO.findAll();
        List newList = new ArrayList<ExperimentProjectSimpleInfo>();

        //获取项目数量
        int listSize = list.size();

        //将实验项目转化简易信息
        for(int i = 0; i < listSize; i++){

            ExperimentProjectSimpleInfo simpleInfo = new ExperimentProjectSimpleInfo(list.get(i));

            newList.add(simpleInfo);
        }

        return newList;
    }

}
