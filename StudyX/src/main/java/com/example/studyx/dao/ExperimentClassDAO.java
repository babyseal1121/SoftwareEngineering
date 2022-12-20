package com.example.studyx.dao;

import com.example.studyx.pojo.ExperimentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperimentClassDAO extends JpaRepository<ExperimentClass,Integer> {

    ExperimentClass findByRandkey(int key);

    ExperimentClass findByExperimentclassno(Integer no);

    List<ExperimentClass> findAll();

}
