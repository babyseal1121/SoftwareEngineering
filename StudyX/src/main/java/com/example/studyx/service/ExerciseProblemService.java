package com.example.studyx.service;

import com.example.studyx.dao.ExerciseProblemDAO;
import com.example.studyx.pojo.ExerciseProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseProblemService {

    @Autowired
    ExerciseProblemDAO exerciseProblemDAO;

    //加入新的题目
    public void createExerciseProblem(ExerciseProblem exerciseProblem){

        exerciseProblemDAO.save(exerciseProblem);
    }

    //删除指定的题目
    public void deleteExerciseProblem(int problemId){

        exerciseProblemDAO.deleteById(problemId);
    }

    //根据实验项目获取题目
    public List<ExerciseProblem> getExperimentProblemList(int experimentNo){

        return exerciseProblemDAO.findByExperimentno(experimentNo);
    }

    //获取一个指定的题目
    public ExerciseProblem getExperimentProblem(int problemNo){

        return exerciseProblemDAO.findById(problemNo);
    }
}
