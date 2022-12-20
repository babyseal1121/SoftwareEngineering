package com.example.studyx.dao;

import com.example.studyx.pojo.ExerciseProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseProblemDAO extends JpaRepository<ExerciseProblem,Integer> {

    List<ExerciseProblem> findByExperimentno(Integer no);

    ExerciseProblem findById(int id);
}
