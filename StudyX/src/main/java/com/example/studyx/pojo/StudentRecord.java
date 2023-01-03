package com.example.studyx.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "studentrecord")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class StudentRecord {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer userid;
    Integer classno;
    Integer attendancenum;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getClassno() {
        return classno;
    }

    public void setClassno(Integer classno) {
        this.classno = classno;
    }

    public Integer getAttendancenum() {
        return attendancenum;
    }

    public void setAttendancenum(Integer attendancenum) {
        this.attendancenum = attendancenum;
    }
}
