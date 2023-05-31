package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "experimentreport")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentReport {

    Integer experimentno;

    Integer userid;

    String username;

    String experimentphenomenon;

    String experimentperception;

    Integer experimentgrade;

    String experimentcomments;

    String experimentsubmissontime;

    String reportpath;

    boolean correctstate;

    @Id
    @Column(name = "experimentreportno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer experimentreportno;

    String experimentname;

    public void setCorrectstate(boolean correctstate) {
        this.correctstate = correctstate;
    }

    public Integer getExperimentreportno() {
        return experimentreportno;
    }

    public void setExperimentreportno(Integer experimentreportno) {
        this.experimentreportno = experimentreportno;
    }

    public Integer getExperimentno() {
        return experimentno;
    }

    public void setExperimentno(Integer experimentno) {
        this.experimentno = experimentno;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExperimentphenomenon() {
        return experimentphenomenon;
    }

    public void setExperimentphenomenon(String experimentphenomenon) {
        this.experimentphenomenon = experimentphenomenon;
    }

    public String getExperimentperception() {
        return experimentperception;
    }

    public void setExperimentperception(String experimentperception) {
        this.experimentperception = experimentperception;
    }

    public Integer getExperimentgrade() {
        return experimentgrade;
    }

    public void setExperimentgrade(Integer experimentgrade) {
        this.experimentgrade = experimentgrade;
    }

    public String getExperimentcomments() {
        return experimentcomments;
    }

    public void setExperimentcomments(String experimentcomments) {
        this.experimentcomments = experimentcomments;
    }

    public String getExperimentsubmissontime() {
        return experimentsubmissontime;
    }

    public void setExperimentsubmissontime(String experimentsubmissontime) {
        this.experimentsubmissontime = experimentsubmissontime;
    }

    public String getExperimentname() {
        return experimentname;
    }

    public void setExperimentname(String experimentname) {
        this.experimentname = experimentname;
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath;
    }
}
