package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exercise")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Exercise {
    @Id
    @Column(name = "exerciseno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer exerciseno;

    String exercisestarttime;
    String exerciseendtime;

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
}
