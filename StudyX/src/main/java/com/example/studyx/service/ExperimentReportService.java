package com.example.studyx.service;

import com.example.studyx.dao.ExperimentClassDAO;
import com.example.studyx.dao.ExperimentProjectDAO;
import com.example.studyx.dao.ExperimentReportDAO;
import com.example.studyx.dao.MemberInClassDAO;
import com.example.studyx.domain.ExperimentReportSimpleInfo;
import com.example.studyx.pojo.ExperimentReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExperimentReportService {

    @Autowired
    ExperimentReportDAO experimentReportDAO;

    //根文件夹地址
    String rootDirPath = "C:/report";
    @Autowired
    private ExperimentClassDAO experimentClassDAO;
    @Autowired
    private MemberInClassDAO memberInClassDAO;
    @Autowired
    private ExperimentProjectDAO experimentProjectDAO;

    //新增实验报告
    public void submitExperimentReport(ExperimentReport experimentReport){

        experimentReportDAO.save(experimentReport);
    }

    //提交实验报告文档
    public String submitExperimentReportFile(MultipartFile studyFile, int userId, int experimentNo, String fileName)
    {
        String filePath = "";

        try{
            // 获取班级名称
            String className = (experimentClassDAO.findByExperimentclassno
                    ((memberInClassDAO.findByUserid(userId)).getExperimentclassno())).getExperimentclassname();
            // 获取实验名称
            String experimentName = (experimentProjectDAO.findById(experimentNo)).getExperimentname();
            //完整文件夹地址
            String fullDirPath = rootDirPath + "/" + className + "/";

            //验证是否有这个地址
            File fileDir = new File(fullDirPath);
            //如果不存在就创建一个文件夹
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            fullDirPath = fullDirPath + "/" + experimentName + "/";

            //验证是否有这个地址
            fileDir = new File(fullDirPath);
            //如果不存在就创建一个文件夹
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            //存储文件
            File file = new File(fullDirPath + fileName);
            studyFile.transferTo(file);

            //返回文件存储路径
            filePath = "/" + className + "/" + experimentName + "/" + fileName;

        }
        catch (Exception e) {
            filePath = "提交实验报告文档失败";
            throw new RuntimeException(e);
        }

        return filePath;
    }

    //批改实验报告
    public void appraiseExperimentReport(int experimentReportNo,int experimentGrade, String experimentComments){

        //查找该实验报告
        ExperimentReport experimentReport = experimentReportDAO.findById(experimentReportNo);
        //修改报告的成绩
        experimentReport.setExperimentgrade(experimentGrade);
        //修改报告的评语
        experimentReport.setExperimentcomments(experimentComments);
        //修改报告的批改状态
        experimentReport.setCorrectstate(true);
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
