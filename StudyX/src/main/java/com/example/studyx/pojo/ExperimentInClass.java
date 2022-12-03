package com.example.studyx.pojo;

import com.example.studyx.domain.ExperimentInClassPK;
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
//此处为复合主键需要主键实体类
@IdClass(ExperimentInClassPK.class)
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
