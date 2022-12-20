package com.example.studyx.dao;

import com.example.studyx.pojo.MemberInExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface MemberInExerciseDAO extends JpaRepository<MemberInExercise,Integer> {

    @Modifying
    @Transactional
    void deleteByExerciseno(Integer exerciseNo);

    List<MemberInExercise> findByExerciseno(Integer exerciseNo);

    List<MemberInExercise> findByUserid(Integer id);

    MemberInExercise findByUseridAndExerciseno(Integer id, Integer no);

}
