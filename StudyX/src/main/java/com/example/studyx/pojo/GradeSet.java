package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "gradeset")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class GradeSet {
    @Id
    @Column(name = "classno")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    Integer classno;
    Integer attandancenum;
    Integer experimentnum;
    Double attandanceweight;
    Double experimentweight;

    public Integer getClassno() {
        return classno;
    }

    public void setClassno(Integer classno) {
        this.classno = classno;
    }

    public Integer getAttandancenum() {
        return attandancenum;
    }

    public void setAttandancenum(Integer attandancenum) {
        this.attandancenum = attandancenum;
    }

    public Integer getExperimentnum() {
        return experimentnum;
    }

    public void setExperimentnum(Integer experimentnum) {
        this.experimentnum = experimentnum;
    }

    public Double getAttandanceweight() {
        return attandanceweight;
    }

    public void setAttandanceweight(Double attandanceweight) {
        this.attandanceweight = attandanceweight;
    }

    public Double getExperimentweight() {
        return experimentweight;
    }

    public void setExperimentweight(Double experimentweight) {
        this.experimentweight = experimentweight;
    }
}
