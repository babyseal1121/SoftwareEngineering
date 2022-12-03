package com.example.studyx.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberInExercisePK implements Serializable {
    Integer id;

    Integer exerciseno;
}
