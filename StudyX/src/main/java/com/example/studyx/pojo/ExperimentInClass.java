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
@Table(name = "experimentinclass")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ExperimentInClass {

    @Id
    @Column(name = "experimentno")
    Integer experimentno;

    @Id
    @Column(name = "experimentclassno")
    Integer experimentclassno;

    public Integer getExperimentno() {
        return experimentno;
    }

    public void setExperimentno(Integer experimentno) {
        this.experimentno = experimentno;
    }

    public Integer getExperimentclassno() {
        return experimentclassno;
    }

    public void setExperimentclassno(Integer experimentclassno) {
        this.experimentclassno = experimentclassno;
    }
}
