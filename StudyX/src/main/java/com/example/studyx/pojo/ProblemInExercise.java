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
@Table(name = "memberinexercise")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ProblemInExercise {

    @Id
    @Column(name = "exerciseno")
    Integer exerciseno;

    @Column(name = "problemno")
    Integer problemno;

}
