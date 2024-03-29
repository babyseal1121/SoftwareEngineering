package com.example.studyx.service;

import com.example.studyx.dao.*;
import com.example.studyx.domain.ExperimentClassInfo;
import com.example.studyx.domain.ExperimentProjectSimpleInfo;
import com.example.studyx.domain.ExperimentReportSimpleInfo;
import com.example.studyx.domain.UserSimpleInfo;
import com.example.studyx.pojo.*;
import com.example.studyx.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

@Service
public class ExperimentClassService {

    @Autowired
    ExperimentClassDAO experimentClassDAO;
    @Autowired
    MemberInClassDAO memberInClassDAO;
    @Autowired
    ExperimentInClassDAO experimentInClassDAO;
    @Autowired
    private ExperimentReportDAO experimentReportDAO;
    @Autowired
    private ExperimentProjectDAO experimentProjectDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    GradeDAO gradeDAO;
    @Autowired
    GradeSetDAO gradesetDAO;

    //根文件夹地址
    String rootDirPath = "C:/report";

    //创建新的班级
    public void createExperimentClass(ExperimentClassInfo experimentClassInfo){

        //生成一个随机码便于找到自增后的课程
        int randKey = new Random().nextInt(10000);

        //存储班级的信息
        ExperimentClass experimentClass = new ExperimentClass(experimentClassInfo, randKey);
        //获取班级人员的信息
        List<Integer> userList = experimentClassInfo.getUsersNo();
        int userListSize = userList.size();
        //获取班级实验项目的信息
        List<Integer>experimentList = experimentClassInfo.getExperimentsNo();
        int experimentListSize = experimentList.size();

        //先存储班级基础信息
        experimentClassDAO.save(experimentClass);
        //记录自增序号
        experimentClass = experimentClassDAO.findByRandkey(randKey);
        int experimentClassNo = experimentClass.getExperimentclassno();

        //存储参与班级人员信息
        for(int i = 0; i < userListSize; i++){

            MemberInClass member = new MemberInClass(userList.get(i), experimentClassNo);
            memberInClassDAO.save(member);
        }
        //存储班级内实验项目的信息
        for(int i = 0; i < experimentListSize; i++){

            ExperimentInClass experimentInClass = new ExperimentInClass(experimentList.get(i), experimentClassNo);
            experimentInClassDAO.save(experimentInClass);
        }

        //清空随机码
        experimentClass.setRandkey(-1);
        //设置归档情况
        experimentClass.setPigeonhole(false);
        experimentClassDAO.save(experimentClass);

        //设置成绩计算预设
        GradeSet gradeSet = new GradeSet();
        gradeSet.setClassno(experimentClass.getExperimentclassno());
        gradeSet.setExperimentnum(experimentListSize);
        gradeSet.setAttandancenum(10);
        gradeSet.setAttandanceweight(0.3);
        gradeSet.setExperimentweight(0.7);
        gradesetDAO.save(gradeSet);

        //设置每个学生的成绩
        for(int i = 0; i < userListSize; i++){

            Grade grade = new Grade(userList.get(i),experimentClass.getExperimentclassno());
            gradeDAO.save(grade);
        }
    }

    //删除班级
    public void deleteExperimentClass(int experimentClassNo){

        //删除班级内成员
        memberInClassDAO.deleteByExperimentclassno(experimentClassNo);
        //删除班级内实验项目
        experimentInClassDAO.deleteByExperimentclassno(experimentClassNo);
        //删除班级基础信息
        experimentClassDAO.deleteById(experimentClassNo);
    }

    //获取班级内的详细信息
    public ExperimentClassInfo getExperimentClass(int userId){

        //查找人员班级信息
        int experimentClassNo = findExperimentClass(userId);
        //如果没有加入班级
        if(-1 == experimentClassNo){

            return null;
        }
        //查找课程基础信息
        ExperimentClass experimentClass = experimentClassDAO.findByExperimentclassno(experimentClassNo);
        //查找有关人员的信息
        List<MemberInClass> userList = memberInClassDAO.findByExperimentclassno(experimentClassNo);
        int userListSize = userList.size();
        //查找有关实验项目的信息
        List<ExperimentInClass> experimentList = experimentInClassDAO.findByExperimentclassno(experimentClassNo);
        int experimentListSize = experimentList.size();
        //存储人员Id
        List<Integer> userIdList = new ArrayList<>();
        //存储实验项目编号
        List<Integer> experimentIdList = new ArrayList<>();

        //提取人员的Id
        for(int i = 0; i < userListSize; i++){

            userIdList.add((userList.get(i)).getUserid());
        }
        //提取项目编号
        for(int i = 0; i < experimentListSize; i++){

            experimentIdList.add((experimentList.get(i)).getExperimentno());
        }

        return new ExperimentClassInfo(experimentClass,userIdList, experimentIdList);

    }

    //查询所有班级信息
    public List<ExperimentClass> getExperimentClassList(){

        return experimentClassDAO.findAll();
    }

    //删除班级内实验报告
    public void deleteClassExperiment(int experimentNo, int experimentClassNo){

        experimentInClassDAO.deleteByExperimentclassnoAndExperimentno(experimentClassNo,experimentNo);
    }

    //向班级内添加实验报告
    public void publishExperiment(int experimentNo, int experimentClassNo){

        experimentInClassDAO.save(new ExperimentInClass(experimentNo, experimentClassNo));
    }

    //向班级内添加人员
    public void addClassMember(int userId, int experimentClassNo){

        // 在班级表内添加人员
        memberInClassDAO.save(new MemberInClass(userId, experimentClassNo));

        Grade grade = new Grade(userId, experimentClassNo);
        System.out.println(grade);

        // 在成绩单内添加人员
        gradeDAO.save(grade);
    }

    //向班级内删除人员
    public void deleteClassMember(int userId, int experimentClassNo){

        memberInClassDAO.deleteByExperimentclassnoAndUserid(experimentClassNo, userId);
    }

    //查找人员所在班级
    public int findExperimentClass(int userId){

        MemberInClass member = memberInClassDAO.findByUserid(userId);

        //若空则返回-1
        if(member != null)
            return member.getExperimentclassno();
        else
            return -1;
    }

    public List<ExperimentReportSimpleInfo> getClassExperimentReportList(int userId){

        //获取班级
        int experimentClassNo = findExperimentClass(userId);
        //如果不在班级里
        if(-1 == experimentClassNo){

            return new ArrayList<>();
        }

        //寻找班级内的所有人
        List<MemberInClass> userList = memberInClassDAO.findByExperimentclassno(experimentClassNo);
        int userListSize = userList.size();
        //存储人员的报告的信息
        List<ExperimentReportSimpleInfo> reportInfo = new ArrayList<>();


        //获取人员的报告
        for(int i = 0; i <userListSize; i++){

            //获取一个人的所有报告
            List<ExperimentReport> reportInfoIn = experimentReportDAO.findByUserid((userList.get(i)).getUserid());
            int reportSize = reportInfoIn.size();
            //转化为简易信息
            List<ExperimentReportSimpleInfo> reportSimpleInfos = new ArrayList<>();
            //提取每个人的信息
            for(int m = 0; m < reportSize; m++){

                reportSimpleInfos.add(new ExperimentReportSimpleInfo(reportInfoIn.get(m)));
            }
            //加入集合
            reportInfo.addAll(reportSimpleInfos);
        }

        return reportInfo;
    }

    //获取班级内的未完成的实验报告简易信息列表
    public List<ExperimentProjectSimpleInfo> getClassExperimentProjectList(int userId){
        //获取班级
        int classNo = findExperimentClass(userId);
        if(-1 == classNo){
            return new ArrayList<>();
        }
        //获取班级的实验信息
        List<ExperimentInClass> list = experimentInClassDAO.findByExperimentclassno(classNo);
        int listSize = list.size();
        //存储结果
        List<ExperimentProjectSimpleInfo> InfoList = new ArrayList<>();
        //获取一个人已经完成的实验报告
        List<ExperimentReport> reportList = experimentReportDAO.findByUserid(userId);
        int reportListSize = reportList.size();
        //获取简易信息列表
        for(int i = 0; i < listSize; i++){
            //实验编号
            int experimentNo = (list.get(i)).getExperimentno();
            ExperimentProject experimentProject = experimentProjectDAO.findById(experimentNo);
            ExperimentProjectSimpleInfo simpleInfo = new ExperimentProjectSimpleInfo(experimentProject);
            InfoList.add(simpleInfo);
        }
        //校验已完成的
        for(int i = 0; i < InfoList.size(); i++){
            for(int n = 0; n < reportListSize; n++){
                //已经完成的
                if((InfoList.get(i).getExperimentno()).equals(reportList.get(n).getExperimentno())){
                    //移除这一项
                    InfoList.remove(i);
                    i--;
                    break;
                }
            }
        }
        return InfoList;
    }

    //获取未加入班级的教师
    public List<UserSimpleInfo> teacherNoInClass(){

        //存储教师的信息
        List<User> teacherInfo = userDAO.findByLevel("教师");
        //存储责任教师的信息
        teacherInfo.addAll(userDAO.findByLevel("责任教师"));
        //记录数量
        int InfoListSize = teacherInfo.size();
        //存储教师简易的信息
        List<UserSimpleInfo> teacherSimInfo = new ArrayList<>();

        //筛选已经加入的老师
        for(int i = 0; i < InfoListSize; i++){
            //移除已经加入的老师
            if(null != memberInClassDAO.findByUserid(teacherInfo.get(i).getId())){
                teacherInfo.remove(i);
                InfoListSize--;
                i--;
            }
        }

        //转化为简易信息形式
        for(int i = 0; i < InfoListSize; i++){

            UserSimpleInfo simpleInfo = new UserSimpleInfo(teacherInfo.get(i));
            teacherSimInfo.add(simpleInfo);
        }

        return teacherSimInfo;
    }

    //获取未加入班级的学生和助教
    public List<UserSimpleInfo> studentNoInClass(){

        //存储学生的信息
        List<User> studentInfo = userDAO.findByLevel("学生");
        //存储助教的信息
        studentInfo.addAll(userDAO.findByLevel("助教"));
        //记录数量
        int InfoListSize = studentInfo.size();
        //存储学生简易的信息
        List<UserSimpleInfo> studentSimInfo = new ArrayList<>();

        //筛选已经加入的学生
        for(int i = 0; i < InfoListSize; i++){
            //移除已经加入的学生
            if(null != memberInClassDAO.findByUserid(studentInfo.get(i).getId())){
                studentInfo.remove(i);
                InfoListSize--;
                i--;
            }
        }

        //转化为简易信息形式
        for(int i = 0; i < InfoListSize; i++){

            UserSimpleInfo simpleInfo = new UserSimpleInfo(studentInfo.get(i));
            studentSimInfo.add(simpleInfo);
        }

        return studentSimInfo;
    }

    //获取班级内的所有学生
    public List<UserSimpleInfo> getAllStudentInClass(int userId){

        //获取班级号
        int experimentClassNo = findExperimentClass(userId);
        //存储信息列表
        List<UserSimpleInfo> userList = new ArrayList<>();
        //获取班级内学生信息
        List<MemberInClass> infoList = memberInClassDAO.findByExperimentclassno(experimentClassNo);
        //获取长度
        int infoListSize = infoList.size();

        //获取学生信息
        for(int i = 0; i< infoListSize; i++){

            UserSimpleInfo user = new UserSimpleInfo(userDAO.getById(infoList.get(i).getUserid()));
            //不加入教师
            //if(!(user.getLevel().equals("教师")))
                userList.add(user);
        }

        return userList;
    }

    //获取班级内实验报告的信息
    public List<ExperimentProjectSimpleInfo> getAllExperimentInClass(int userId){

        //获取班级号
        int experimentClassNo = findExperimentClass(userId);
        //存储信息列表
        List<ExperimentProjectSimpleInfo> experimentList = new ArrayList<>();
        //获取班级内报告信息
        List<ExperimentInClass> infoList = experimentInClassDAO.findByExperimentclassno(experimentClassNo);
        //获取长度
        int infoListSize = infoList.size();

        //获取报告信息
        for(int i = 0; i< infoListSize; i++){
            int experimentNo = infoList.get(i).getExperimentno();
            ExperimentProjectSimpleInfo simpleInfo = new ExperimentProjectSimpleInfo(experimentProjectDAO.findById(experimentNo));
            experimentList.add(simpleInfo);
        }

        return experimentList;
    }

    //将班级归档
    public String pigeonholeExperimentClass(int experimentClassNo){
        //获取班级信息
        ExperimentClass experimentClass = experimentClassDAO.findByExperimentclassno(experimentClassNo);

        //查看班级是否已经归档
        if(experimentClass.isPigeonhole())
        {
            return new String("该班级已经归档");
        }

        //将班级内学生设为未激活状态
        //查询班级内学生
        List<MemberInClass> member = memberInClassDAO.findByExperimentclassno(experimentClassNo);
        //将学生状态改为未激活
        for(int i = 0; i < member.size(); i++)
        {
            User user = userDAO.getById(member.get(i).getUserid());
            if(user.getLevel().equals("助教") || user.getLevel().equals("学生"))
            {
                user.setLevel("未激活");
                userDAO.save(user);
            }
        }
        //将班级改为归档状态
        experimentClass.setPigeonhole(true);
        experimentClassDAO.save(experimentClass);

        return new String("班级归档成功");
    }

    //递归压缩
    private static void compress(File sourceFile, ZipOutputStream zos, String name,
                                 boolean KeepDirStructure) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }

            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }

                }
            }
        }
    }

    //获取班级文件的Zip包
    public Result getExperimentInClassZip(HttpServletResponse response, int experimentClassNo){
        //获取班级名称
        String className = experimentClassDAO.findByExperimentclassno(experimentClassNo).getExperimentclassname();
        //获取班级文件地址
        String strDir = rootDirPath + "/" + className;
        //判断是否存在文件夹
        File fileDir = new File(strDir);
        //如果不存在就返回
        if (!fileDir.exists()) {

            return new Result(400,"failure","该班级当前没有文件");
        }

        //验证是否有压缩好的文件有了就不再压缩了(算了不合理)
        //获取文件位置
        //File zipFile = new File(rootDirPath + "/" + className + ".zip");
        //判断文件是否存在
        if(true) {
            try {
                //进行压缩
                ZipOutputStream zos = null;
                FileOutputStream fos = new FileOutputStream(new File(rootDirPath + "/" + className + ".zip"));
                try {
                    zos = new ZipOutputStream(fos);
                    File sourceFile = new File(strDir);
                    compress(sourceFile, zos, sourceFile.getName(), true);
                } catch (Exception e) {
                    throw new RuntimeException("zip error from ZipUtils", e);
                } finally {
                    if (zos != null) {
                        try {
                            zos.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //验证是否有压缩好的文件
        //获取文件位置
        File file = new File(rootDirPath + "/" + className + ".zip");
        //判断文件是否存在
        if(!file.exists()){
            return new Result(400,"failure","文件不存在文件下载失败");
        }

        //重设请求头信息
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        String formFileName = "";
        try {
            String fileName = className + ".zip";
            formFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + formFileName );

        //开始传输文件
        try{
            BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            System.out.println(e);;
            return new Result(400,"failure","文件下载失败");
        }

        return new Result(200,"success","文件下载成功");
    }
}
