package com.example.studyx.service;

import com.example.studyx.dao.ExerciseDAO;
import com.example.studyx.dao.MemberInExerciseDAO;
import com.example.studyx.dao.ProblemInExerciseDAO;
import com.example.studyx.domain.ExerciseInfo;
import com.example.studyx.domain.ExerciseResult;
import com.example.studyx.pojo.Exercise;
import com.example.studyx.pojo.MemberInExercise;
import com.example.studyx.pojo.ProblemInExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExerciseService {

    @Autowired
    ExerciseDAO exerciseDAO;
    @Autowired
    MemberInExerciseDAO memberInExerciseDAO;
    @Autowired
    ProblemInExerciseDAO problemInExerciseDAO;

    //发布一场测试
    public void publishExercise(ExerciseInfo exerciseInfo){

        //生成一个随机码便于找到自增后的测试
        int randKey = new Random().nextInt(10000);

        //获取测试的基本信息
        Exercise exercise = new Exercise(exerciseInfo.getExerciseno(), exerciseInfo.getExercisestarttime(),exerciseInfo.getExerciseendtime(), randKey);
        //获取测试的人员信息
        List<Integer> userList = exerciseInfo.getUsersNo();
        int userListSize = userList.size();
        //获取测试的试题信息
        List<Integer> problemList = exerciseInfo.getProblemNo();
        int problemListSize = problemList.size();

        //存储考试基础信息
        exerciseDAO.save(exercise);

        //记录生成的自增值
        exercise = exerciseDAO.findByRandkey(randKey);
        int exerciseNo = exercise.getExerciseno();


        //存储测试的人员信息
        for(int i = 0; i < userListSize; i++){

            MemberInExercise memberInExercise = new MemberInExercise(userList.get(i),exerciseNo,0,0);
            memberInExerciseDAO.save(memberInExercise);
        }
        //存储测试的题目信息
        for(int i = 0; i < problemListSize; i++){

            ProblemInExercise problemInExercise = new ProblemInExercise(exerciseNo, problemList.get(i));
            problemInExerciseDAO.save(problemInExercise);
        }

        //清空随机码
        exercise.setRandkey(-1);
        exerciseDAO.save(exercise);

    }

    //删除一场测试
    public void deleteExercise(int exerciseNo){

        //删除参与测试的人员
        memberInExerciseDAO.deleteByExerciseno(exerciseNo);
        //删除测试内的题目
        problemInExerciseDAO.deleteByExerciseno(exerciseNo);
        //删除测试本身
        exerciseDAO.deleteById(exerciseNo);
    }

    //获取一个测试的详细信息
    public ExerciseInfo getExercise(int exerciseNo){

        //获取考试的基础信息
        Exercise exercise = exerciseDAO.findById(exerciseNo);
        //获取考试的人员信息
        List<MemberInExercise> userList = memberInExerciseDAO.findByExerciseno(exerciseNo);
        int userListSize = userList.size();
        //获取考试的题目信息
        List<ProblemInExercise> problemList = problemInExerciseDAO.findByExerciseno(exerciseNo);
        int problemListSize = problemList.size();
        //存储考试的人员信息
        List<Integer> userIdList = new ArrayList<>();
        //存储考试的题目信息
        List<Integer> problemIdList = new ArrayList<>();

        //提取考试的人员信息
        for(int i = 0; i < userListSize; i++){

            userIdList.add((userList.get(i)).getUserid());
        }

        //提取题目的信息
        for(int i = 0; i < problemListSize; i++){

            problemIdList.add((problemList.get(i)).getProblemno());
        }

        return (new ExerciseInfo(exercise.getExerciseno(), exercise.getExercisestarttime(), exercise.getExerciseendtime(), userIdList, problemIdList));
    }

    //获取一个人测试的所有基础信息
    public List<Exercise> getExerciseList(Integer userId){

        //获取参加的测试记录
        List<MemberInExercise> exerciseInList = memberInExerciseDAO.findByUserid(userId);
        int listSize = exerciseInList.size();
        //存储参见的测试
        List<Exercise> exerciseList = new ArrayList<>();

        //提取参加的测试
        for(int i = 0; i < listSize; i++){

            int exerciseNo = (exerciseInList.get(i)).getExerciseno();
            exerciseList.add(exerciseDAO.findById(exerciseNo));
        }

        return exerciseList;
    }

    //获取某场考试某人的详细信息
    public ExerciseResult getExerciseResult(int userId, int exerciseNo){

        //获取特定人在特定考试中信息
        MemberInExercise memberInExercise = memberInExerciseDAO.findByUseridAndExerciseno(userId, exerciseNo);
        //获取考试的基础信息
        Exercise exercise = exerciseDAO.findById(exerciseNo);

        return(new ExerciseResult(memberInExercise,exercise));
    }

    //存储某人的提交结果
    public void submitExercise(MemberInExercise member){

        memberInExerciseDAO.save(member);
    }
}
