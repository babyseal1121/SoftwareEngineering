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
@Table(name = "experimentproject")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentProject {

    @Id
    @Column(name = "experimentno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer experimentno;

    String experimentname;

    String experimentpurpose;

    String experimentbasicknowledge;

    String experimentsteps;

    String experimentquestion;

    public Integer getExperimentno() {
        return experimentno;
    }

    public void setExperimentno(Integer experimentno) {
        this.experimentno = experimentno;
    }

    public String getExperimentname() {
        return experimentname;
    }

    public void setExperimentname(String experimentname) {
        this.experimentname = experimentname;
    }

    public String getExperimentpurpose() {
        return experimentpurpose;
    }

    public void setExperimentpurpose(String experimentpurpose) {
        this.experimentpurpose = experimentpurpose;
    }

    public String getExperimentbasicknowledge() {
        return experimentbasicknowledge;
    }

    public void setExperimentbasicknowledge(String experimentbasicknowledge) {
        this.experimentbasicknowledge = experimentbasicknowledge;
    }

    public String getExperimentsteps() {
        return experimentsteps;
    }

    public void setExperimentsteps(String experimentsteps) {
        this.experimentsteps = experimentsteps;
    }

    public String getExperimentquestion() {
        return experimentquestion;
    }

    public void setExperimentquestion(String experimentquestion) {
        this.experimentquestion = experimentquestion;
    }
}
