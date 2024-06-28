package com.infy.student_crud_app_1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_id_genarator")
    @SequenceGenerator(name = "student_id_genarator", sequenceName = "student_id_genarator", initialValue = 100, allocationSize = 1)
    Integer studentId;
    String name;
    String clgName;
    String branch;

    public Student() {
    }

    public Student(Integer studentId, String name, String clgName, String branch) {
        this.studentId = studentId;
        this.name = name;
        this.clgName = clgName;
        this.branch = branch;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClgName() {
        return clgName;
    }

    public void setClgName(String clgName) {
        this.clgName = clgName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
