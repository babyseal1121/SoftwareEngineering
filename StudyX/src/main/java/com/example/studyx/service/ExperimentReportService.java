package com.example.studyx.service;

import com.example.studyx.dao.ExperimentReportDAO;
import com.example.studyx.domain.ExperimentReportSimpleInfo;
import com.example.studyx.pojo.ExperimentReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperimentReportService {

    @Autowired
    ExperimentReportDAO experimentReportDAO;

    //新增实验报告
    public void submitExperimentReport(ExperimentReport experimentReport){

        experimentReportDAO.save(experimentReport);
    }

    //批改实验报告
    public void appraiseExperimentReport(int experimentReportNo,int experimentGrade, String experimentComments){

        //查找该实验报告
        ExperimentReport experimentReport = experimentReportDAO.findById(experimentReportNo);
        //修改报告的成绩
        experimentReport.setExperimentgrade(experimentGrade);
        //修改报告的评语
        experimentReport.setExperimentcomments(experimentComments);
        //更新
        experimentReportDAO.save(experimentReport);
    }

    //获取实验报告详细内容
    public ExperimentReport getExperimentReport(int experimentReportNo){

        return experimentReportDAO.findById(experimentReportNo);
    }

    //获取学生本人的所有实验报告
    public List<ExperimentReportSimpleInfo> getExperimentReportList(int userId){

        //获取所有的实验报告
        List<ExperimentReport> list = experimentReportDAO.findByUserid(userId);
        //存放实验报告简略的信息
        List newList = new ArrayList<ExperimentReportSimpleInfo>();

        //记录获取了多少报告
        int listSize = list.size();

        //转化
        for(int i = 0; i < listSize; i++){

            ExperimentReportSimpleInfo simpleInfo = new ExperimentReportSimpleInfo(list.get(i));

            newList.add(simpleInfo);
        }

        return newList;
    }
}
