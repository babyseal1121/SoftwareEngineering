package com.example.studyx.dao;

import com.example.studyx.pojo.ExperimentProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperimentProjectDAO extends JpaRepository<ExperimentProject,Integer> {

    ExperimentProject findById (int Id);

    List<ExperimentProject> findAll();
}
