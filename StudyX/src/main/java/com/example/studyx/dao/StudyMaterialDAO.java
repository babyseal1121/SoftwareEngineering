package com.example.studyx.dao;

import com.example.studyx.pojo.StudyMaterial;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface StudyMaterialDAO extends JpaRepository<StudyMaterial,Integer> {

    StudyMaterial findByMaterialno(Integer no);

    @Modifying
    @Transactional
    void deleteByMaterialno(Integer no);

    List<StudyMaterial> findByMaterialpath(String path);

    StudyMaterial findByMaterialpathAndMaterialname(String path, String name);

    @NotNull
    List<StudyMaterial> findAll();

}
