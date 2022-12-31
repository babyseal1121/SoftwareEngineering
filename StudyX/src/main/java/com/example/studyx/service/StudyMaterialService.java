package com.example.studyx.service;


import com.example.studyx.dao.StudyMaterialDAO;
import com.example.studyx.pojo.StudyMaterial;
import com.example.studyx.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
public class StudyMaterialService {

    @Autowired
    StudyMaterialDAO studyMaterialDAO;

    //根文件夹地址
    String rootDirPath = "C:/book";

    //上传文件
    public void submitStudyMaterial(MultipartFile studyFile, String fileName, String pathName, String uploadTime){

        try {
            //完整文件地址
            String fullDirPath = rootDirPath + pathName;

            //验证是否有这个地址
            File fileDir = new File(fullDirPath);
            //如果不存在就创建一个文件夹
            if (!fileDir.exists()) {

                fileDir.mkdir();
            }

            //存储文件
            File file = new File(fullDirPath + fileName);
            studyFile.transferTo(file);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        studyMaterialDAO.save(new StudyMaterial(0, fileName, pathName, uploadTime));

    }

    //下载文件
    public Result getStudyMaterial(HttpServletResponse response, Integer materialNo){

        //获取文件信息
        StudyMaterial studyMaterial = studyMaterialDAO.findByMaterialno(materialNo);
        //判断文件在数据库内是否存在
        if(null == studyMaterial){

            return new Result(400,"failure","无效的文件名");
        }
        String pathName = studyMaterial.getMaterialpath();
        String fileName = studyMaterial.getMaterialname();

        //获取文件位置
        File file = new File(rootDirPath + pathName + fileName);
        //判断文件是否存在
        if(!file.exists()){

            return new Result(400,"failure","文件不存在文件下载失败");
        }
        //重设请求头信息
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
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

    //删除文件
    public String deleteStudyMaterial(int materialNo){

        //获取文件信息
        StudyMaterial studyMaterial = studyMaterialDAO.findByMaterialno(materialNo);
        //判断文件是否存在
        if(null == studyMaterial){

            return "删除文件失败文件不存在";
        }
        //获取文件的存储位置
        String fullFileName = rootDirPath + studyMaterial.getMaterialpath() + studyMaterial.getMaterialname();

        //删除文件
        try {
            FileSystemUtils.deleteRecursively(new File(fullFileName));
        }
        catch (Exception e){

            return "删除文件失败";
        }

        //在数据库中将文件信息删除
        studyMaterialDAO.deleteByMaterialno(materialNo);
        //检查该文件夹下还有无文件
        List<StudyMaterial> list = studyMaterialDAO.findByMaterialpath(studyMaterial.getMaterialpath());
        //如果没有了就删除文件夹
        if(0 == list.size()){

            FileSystemUtils.deleteRecursively(new File(rootDirPath + studyMaterial.getMaterialpath()));
        }

        return "删除文件成功";
    }

    //改变文件存储位置
    public String changeStudyMaterialPath(int materialNo, String NewMaterialPath){

        //获取文件信息
        StudyMaterial studyMaterial = studyMaterialDAO.findByMaterialno(materialNo);
        //判断文件是否存在
        if(null == studyMaterial){

            return "移动文件失败文件不存在";
        }
        //获取文件的旧存储位置
        String oldFullFileName = rootDirPath + studyMaterial.getMaterialpath() + studyMaterial.getMaterialname();
        //获取文件新的存储位置
        String newFullFileName = rootDirPath + NewMaterialPath;
        //判断新位置有没有文件夹
        File fileDir = new File(newFullFileName);
        //如果不存在文件夹就创建文件夹
        if(!fileDir.exists()){

            fileDir.mkdir();
        }

        //获取新文件的位置
        newFullFileName += studyMaterial.getMaterialname();

        //打开旧文件位置
        File oldFile = new File(oldFullFileName);
        //打开新文件位置
        File newFile = new File(newFullFileName);

        //将旧文件转移到新文件
        if(oldFile.renameTo(newFile)){
        //如果成功
            //记录老文件夹的位置
            String oldPath = studyMaterial.getMaterialpath();
            //修改数据库中的内容
            studyMaterial.setMaterialpath(NewMaterialPath);
            studyMaterialDAO.save(studyMaterial);
            //检查原来文件夹中是否还存在文件
            List<StudyMaterial> list = studyMaterialDAO.findByMaterialpath(oldPath);
            //如果没有了就删除原来的文件夹
            if(0 == list.size()){
                FileSystemUtils.deleteRecursively(new File(rootDirPath + oldPath));
            }
            return "转移文件成功";
        }
        else{
            return "转移位置存在重名文件";
        }

    }

    //获取文件信息列表
    public List<StudyMaterial> getStudyMaterialList(String experimentName){

        return studyMaterialDAO.findByMaterialpath(experimentName);
    }

}
