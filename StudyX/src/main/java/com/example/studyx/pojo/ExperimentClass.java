package com.example.studyx.pojo;

import com.example.studyx.domain.ExperimentClassInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "experimentclass")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentClass {

    @Id
    @Column(name = "experimentclassno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer experimentclassno;

    Integer teacheridnumber;

    String classstarttime;

    String experimentclassname;

    int randkey;

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

    public int getRandkey() {
        return randkey;
    }

    public void setRandkey(int randkey) {
        this.randkey = randkey;
    }

    public ExperimentClass(ExperimentClassInfo experimentClassInfo, int randKey) {

        experimentclassno = experimentClassInfo.getExperimentclassno();
        teacheridnumber = experimentClassInfo.getTeacheridnumber();
        classstarttime = experimentClassInfo.getClassstarttime();
        experimentclassname = experimentClassInfo.getExperimentclassname();
        randkey = randKey;
    }
}
