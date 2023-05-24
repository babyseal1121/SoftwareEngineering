package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "grade")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Grade {
    @Id
    @Column(name = "userid")
    // @GeneratedValue(strategy = GenerationType.AUTO)
    Integer userid;
    double attandancegrade;
    double experimentgrade;
    double totalgrade;

    Integer classno;

    Double attendancenum;

    public Double getAttendancenum() {
        return attendancenum;
    }

    public void setAttendancenum(Double attendancenum) {
        this.attendancenum = attendancenum;
    }

    public Integer getClassno() {
        return classno;
    }

    public void setClassno(Integer classno) {
        this.classno = classno;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public double getAttandancegrade() {
        return attandancegrade;
    }

    public void setAttandancegrade(double attandancegrade) {
        this.attandancegrade = attandancegrade;
    }

    public double getExperimentgrade() {
        return experimentgrade;
    }

    public void setExperimentgrade(double experimentgrade) {
        this.experimentgrade = experimentgrade;
    }

    public double getTotalgrade() {
        return totalgrade;
    }

    public void setTotalgrade(double totalgrade) {
        this.totalgrade = totalgrade;
    }

    // 利用班级以及人员信息生成成绩对象
    public Grade(int userIdIn, int experimentClassNoIn)
    {
        this.userid = userIdIn;
        this.classno = experimentClassNoIn;
        this.attandancegrade = 0.0;
        this.experimentgrade = 0.0;
        this.totalgrade = 0.0;
        this.attendancenum = 0.0;
    }
}
