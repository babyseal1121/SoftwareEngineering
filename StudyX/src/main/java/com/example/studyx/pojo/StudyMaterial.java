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
@Table(name = "studymaterial")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class StudyMaterial {

    @Id
    @Column(name = "materialno")
    Integer materialno;

    String materialname;

    String materialpath;

    String materialuploadtime;

    public Integer getMaterialno() {
        return materialno;
    }

    public void setMaterialno(Integer materialno) {
        this.materialno = materialno;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getMaterialpath() {
        return materialpath;
    }

    public void setMaterialpath(String materialpath) {
        this.materialpath = materialpath;
    }

    public String getMaterialuploadtime() {
        return materialuploadtime;
    }

    public void setMaterialuploadtime(String materialuploadtime) {
        this.materialuploadtime = materialuploadtime;
    }
}
