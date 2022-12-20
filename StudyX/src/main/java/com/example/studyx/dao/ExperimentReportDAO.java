package com.example.studyx.dao;

import com.example.studyx.pojo.ExperimentReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperimentReportDAO extends JpaRepository<ExperimentReport,Integer> {

    ExperimentReport findById (int id);

    List<ExperimentReport> findByUserid(int userid);
}
