package com.example.studyx.domain;

import com.example.studyx.pojo.ExperimentReport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentReportSimpleInfo {

    //实验编号
    Integer experimentno;
    //实验名称
    String experimentname;
    //学生Id
    Integer userid;
    //用户名
    String username;
    //报告编号
    Integer experimentreportno;

    public ExperimentReportSimpleInfo(ExperimentReport experimentReport) {

        experimentno = experimentReport.getExperimentno();
        experimentname = experimentReport.getExperimentname();
        userid = experimentReport.getUserid();
        username = experimentReport.getUsername();
        experimentreportno = experimentReport.getExperimentreportno();
    }
}
