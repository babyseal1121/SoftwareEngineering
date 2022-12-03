package com.example.studyx.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProblemInExercisePK implements Serializable {

    Integer exerciseno;

    Integer problemno;
}
