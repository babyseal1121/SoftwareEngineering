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
    String exerciseno;

    String exercisestarttime;
    String exerciseendtime;

    public String getExerciseno() {
        return exerciseno;
    }

    public void setExerciseno(String exerciseno) {
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
