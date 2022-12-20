package com.example.studyx.pojo;

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
@Table(name = "exerciseproblem")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExerciseProblem {

    String problemdescription;

    @Id
    @Column(name = "problemno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer problemno;

    Integer experimentno;

    String experimentname;

    String problemoptiona;

    String problemoptionb;

    String problemoptionc;

    String problemoptiond;

    String problemanswer;

    public Integer getProblemno() {
        return problemno;
    }

    public void setProblemno(Integer problemno) {
        this.problemno = problemno;
    }

    public Integer getExperimentno() {
        return experimentno;
    }

    public void setExperimentno(Integer experimentno) {
        this.experimentno = experimentno;
    }

    public String getProblemdescription() {
        return problemdescription;
    }

    public void setProblemdescription(String problemdescription) {
        this.problemdescription = problemdescription;
    }

    public String getExperimentname() {
        return experimentname;
    }

    public void setExperimentname(String experimentname) {
        this.experimentname = experimentname;
    }

    public String getProblemoptiona() {
        return problemoptiona;
    }

    public void setProblemoptiona(String problemoptiona) {
        this.problemoptiona = problemoptiona;
    }

    public String getProblemoptionb() {
        return problemoptionb;
    }

    public void setProblemoptionb(String problemoptionb) {
        this.problemoptionb = problemoptionb;
    }

    public String getProblemoptionc() {
        return problemoptionc;
    }

    public void setProblemoptionc(String problemoptionc) {
        this.problemoptionc = problemoptionc;
    }

    public String getProblemoptiond() {
        return problemoptiond;
    }

    public void setProblemoptiond(String problemoptiond) {
        this.problemoptiond = problemoptiond;
    }

    public String getProblemanswer() {
        return problemanswer;
    }

    public void setProblemanswer(String problemanswer) {
        this.problemanswer = problemanswer;
    }
}
