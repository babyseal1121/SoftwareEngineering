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
@Table(name = "probleminexercise")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ProblemInExercise {

    @Id
    @Column(name = "problemno")
    Integer problemno;

    @Id
    @Column(name = "exerciseno")
    Integer exerciseno;

    public Integer getProblemno() {
        return problemno;
    }

    public void setProblemno(Integer problemno) {
        this.problemno = problemno;
    }

    public Integer getExerciseno() {
        return exerciseno;
    }

    public void setExerciseno(Integer exerciseno) {
        this.exerciseno = exerciseno;
    }
}
