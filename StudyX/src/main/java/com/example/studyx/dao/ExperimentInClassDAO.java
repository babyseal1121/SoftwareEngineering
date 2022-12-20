package com.example.studyx.dao;

import com.example.studyx.pojo.ExperimentInClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface ExperimentInClassDAO extends JpaRepository<ExperimentInClass,Integer> {

    @Modifying
    @Transactional
    void deleteByExperimentclassno(Integer no);

    List<ExperimentInClass> findByExperimentclassno(Integer no);

    @Modifying
    @Transactional
    void deleteByExperimentclassnoAndExperimentno(Integer classNo, Integer no);
}
