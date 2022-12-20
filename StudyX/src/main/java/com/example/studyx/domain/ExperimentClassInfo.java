package com.example.studyx.domain;

import com.example.studyx.pojo.ExperimentClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
//用于记录课程的全部信息
public class ExperimentClassInfo {

    //课程的编号
    Integer experimentclassno;
    //教师的id
    Integer teacheridnumber;
    //开班时间
    String classstarttime;
    //课程名称
    String experimentclassname;
    //课程的成员
    List<Integer> usersNo;
    //课程的实验项目
    List<Integer> experimentsNo;

    public Integer getExperimentclassno() {
        return experimentclassno;
    }

    public void setExperimentclassno(Integer experimentclassno) {
        this.experimentclassno = experimentclassno;
    }

    public Integer getTeacheridnumber() {
        return teacheridnumber;
    }

    public void setTeacheridnumber(Integer teacheridnumber) {
        this.teacheridnumber = teacheridnumber;
    }

    public String getClassstarttime() {
        return classstarttime;
    }

    public void setClassstarttime(String classstarttime) {
        this.classstarttime = classstarttime;
    }

    public String getExperimentclassname() {
        return experimentclassname;
    }

    public void setExperimentclassname(String experimentclassname) {
        this.experimentclassname = experimentclassname;
    }

    public List<Integer> getUsersNo() {
        return usersNo;
    }

    public void setUsersNo(List<Integer> usersNo) {
        this.usersNo = usersNo;
    }

    public List<Integer> getExperimentsNo() {
        return experimentsNo;
    }

    public void setExperimentsNo(List<Integer> experimentsNo) {
        this.experimentsNo = experimentsNo;
    }

    public ExperimentClassInfo(ExperimentClass experimentClass, List<Integer> usersNoIn, List<Integer> experimentsNoIn) {

        experimentclassno = experimentClass.getExperimentclassno();
        teacheridnumber = experimentClass.getTeacheridnumber();
        classstarttime = experimentClass.getClassstarttime();
        experimentclassname = experimentClass.getExperimentclassname();

        usersNo = usersNoIn;
        experimentsNo = experimentsNoIn;
    }
}
