package com.example.studyx.domain;

import com.example.studyx.pojo.Exercise;
import com.example.studyx.pojo.MemberInExercise;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExerciseResult {

    //测试号
    Integer exerciseno;
    //测试人Id
    Integer userid;
    //测试开始时间
    String exercisestarttime;
    //测试结束时间
    String exerciseendtime;
    //测试用时
    Integer exerciseusetime;
    //测试成绩
    Integer exercisegrade;

    public Integer getExerciseno() {
        return exerciseno;
    }

    public void setExerciseno(Integer exerciseno) {
        this.exerciseno = exerciseno;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getExerciseusetime() {
        return exerciseusetime;
    }

    public void setExerciseusetime(Integer exerciseusetime) {
        this.exerciseusetime = exerciseusetime;
    }

    public Integer getExercisegrade() {
        return exercisegrade;
    }

    public void setExercisegrade(Integer exercisegrade) {
        this.exercisegrade = exercisegrade;
    }

    public ExerciseResult(MemberInExercise member, Exercise exercise) {

        exerciseno = exercise.getExerciseno();
        exercisestarttime = exercise.getExercisestarttime();
        exerciseendtime = exercise.getExerciseendtime();
        userid = member.getUserid();
        exercisegrade = member.getExercisegrade();
        exerciseusetime = member.getExerciseusetime();
    }
}
