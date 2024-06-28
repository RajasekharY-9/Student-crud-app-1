package com.infy.student_crud_app_1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_id_genarator")
    @SequenceGenerator(name = "student_id_genarator", sequenceName = "student_id_genarator", initialValue = 100, allocationSize = 1)
    Integer studentId;
    String name;
    String clgName;
    String branch;
}
