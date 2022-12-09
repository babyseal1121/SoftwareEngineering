package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "experimentclass")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentReport {

    @Id
    @Column(name = "experimentreportno")
    Integer experimentreportno;

    Integer experimentno;

    Integer id;

    String username;

    String experimentphenomenon;

    String experimentperception;

    Integer experimentgrade;

    String experimentcomments;

    String experimentsubmissontime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
