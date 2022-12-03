package com.example.studyx.pojo;

import com.example.studyx.domain.MemberInClassPK;
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
@IdClass(MemberInClassPK.class)
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
