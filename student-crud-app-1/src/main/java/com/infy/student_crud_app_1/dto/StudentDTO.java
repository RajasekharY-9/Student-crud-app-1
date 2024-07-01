package com.infy.student_crud_app_1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//DTO -> Data transfer object
//It will have the validations and the data that is to be transferred to the client
//SOLID principles -> Single responsibility principle means that a class should have only one reason to change
public class StudentDTO {
    Integer studentId;

    @NotEmpty(message = "{name.not.empty}")//check for null and empty
    String name;
    @NotEmpty(message = "{clgname.not.empty}")

    String clgName;
    @NotEmpty(message = "{branch.not.empty}")

    String branch;

    public StudentDTO() {
    }

    public StudentDTO(Integer studentId, String name, String clgName, String branch) {
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
