package com.example.studyx.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
//用于记录传来的创建测试的消息
public class ExerciseInfo {

    //测试号
    Integer exerciseno;
    //测试开始时间
    String exercisestarttime;
    //测试结束时间
    String exerciseendtime;
    //记录参与测试的人
    List<Integer> usersNo;
    //记录参与测试的题目
    List<Integer> problemNo;

    public Integer getExerciseno() {
        return exerciseno;
    }

    public void setExerciseno(Integer exerciseno) {
        this.exerciseno = exerciseno;
    }

    public String getExercisestarttime() {
        return exercisestarttime;
    }

    public void setExercisestarttime(String exercisestarttime) {
        this.exercisestarttime = exercisestarttime;
    }

    public String getExerciseendtime() {
        return exerciseendtime;
    }

    public void setExerciseendtime(String exerciseendtime) {
        this.exerciseendtime = exerciseendtime;
    }

    public List<Integer> getUsersNo() {
        return usersNo;
    }

    public void setUsersNo(List<Integer> usersNo) {
        this.usersNo = usersNo;
    }

    public List<Integer> getProblemNo() {
        return problemNo;
    }

    public void setProblemNo(List<Integer> problemNo) {
        this.problemNo = problemNo;
    }
}
