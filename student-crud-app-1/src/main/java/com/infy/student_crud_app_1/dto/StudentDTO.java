package com.infy.student_crud_app_1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class StudentDTO {
    Integer studentId;
    @NotNull(message = "{name.not.empty}")
    @NotEmpty(message = "{name.not.empty}")
    String name;
    @NotEmpty(message = "{clgname.not.empty}")
    @NotNull(message = "{clgname.not.empty}")
    String clgName;
    @NotEmpty(message = "{branch.not.empty}")
    @NotNull(message = "{branch.not.empty}")
    String branch;
}
