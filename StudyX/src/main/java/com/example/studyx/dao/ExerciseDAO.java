package com.example.studyx.dao;

import com.example.studyx.pojo.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseDAO extends JpaRepository<Exercise,Integer>{
    Exercise findByExerciseno(String exerciseno);
}
