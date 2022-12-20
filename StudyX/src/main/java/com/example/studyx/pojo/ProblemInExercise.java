package com.example.studyx.pojo;

import com.example.studyx.domain.ProblemInExercisePK;
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
@IdClass(ProblemInExercisePK.class)
@Table(name = "probleminexercise")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ProblemInExercise {

    @Id
    @Column(name = "exerciseno")
    Integer exerciseno;

    @Id
    @Column(name = "problemno")
    Integer problemno;

    public Integer getExerciseno() {
        return exerciseno;
    }

    public void setExerciseno(Integer exerciseno) {
        this.exerciseno = exerciseno;
    }

    public Integer getProblemno() {
        return problemno;
    }

    public void setProblemno(Integer problemno) {
        this.problemno = problemno;
    }
}
