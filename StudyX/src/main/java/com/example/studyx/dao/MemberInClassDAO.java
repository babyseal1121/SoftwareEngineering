package com.example.studyx.dao;

import com.example.studyx.pojo.MemberInClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface MemberInClassDAO extends JpaRepository<MemberInClass,Integer> {

    @Modifying
    @Transactional
    void deleteByExperimentclassno(Integer no);

    List<MemberInClass> findByExperimentclassno(Integer no);

    @Modifying
    @Transactional
    void deleteByExperimentclassnoAndUserid(Integer no, Integer id);

    MemberInClass findByUserid(Integer id);
}
