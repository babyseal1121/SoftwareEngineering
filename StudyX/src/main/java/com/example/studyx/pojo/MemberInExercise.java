package com.example.studyx.pojo;

import com.example.studyx.domain.MemberInExercisePK;
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
@IdClass(MemberInExercisePK.class)
@Table(name = "memberinexercise")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class MemberInExercise {

    @Id
    @Column(name = "id")
    Integer id;

    @Id
    @Column(name = "exerciseno")
    Integer exerciseno;

    Integer exerciseusetime;

    Integer exercisegrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExerciseno() {
        return exerciseno;
    }

    public void setExerciseno(Integer exerciseno) {
        this.exerciseno = exerciseno;
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
}