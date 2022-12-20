package com.example.studyx.dao;

import com.example.studyx.pojo.ProblemInExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface ProblemInExerciseDAO extends JpaRepository<ProblemInExercise,Integer> {

    @Modifying
    @Transactional
    void deleteByExerciseno(Integer exerciseNo);

    List<ProblemInExercise> findByExerciseno(Integer exerciseNo);
}
