package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    Integer experimentclassno;

    Integer teacheridnumber;

    String classstarttime;

    String experimentclassname;

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
}
