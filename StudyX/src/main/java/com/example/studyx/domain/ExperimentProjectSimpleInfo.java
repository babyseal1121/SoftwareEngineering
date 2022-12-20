package com.example.studyx.domain;

import com.example.studyx.pojo.ExperimentProject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

//存放实验报告的简易信息
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentProjectSimpleInfo {

    //实验编号
    Integer experimentno;
    //实验名称
    String experimentname;

    public ExperimentProjectSimpleInfo(ExperimentProject experimentProject) {

        experimentno = experimentProject.getExperimentno();
        experimentname = experimentProject.getExperimentname();
    }

    public void setExperimentProjectSimpleInfo(ExperimentProject experimentProject) {
        experimentno = experimentProject.getExperimentno();
        experimentname = experimentProject.getExperimentname();
    }
}
