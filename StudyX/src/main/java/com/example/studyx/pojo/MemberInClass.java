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
@Table(name = "memberinclass")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class MemberInClass {

    @Id
    @Column(name = "id")
    Integer id;

    @Id
    @Column(name = "experimentclassno")
    Integer experimentclassno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExperimentclassno() {
        return experimentclassno;
    }

    public void setExperimentclassno(Integer experimentclassno) {
        this.experimentclassno = experimentclassno;
    }
}
