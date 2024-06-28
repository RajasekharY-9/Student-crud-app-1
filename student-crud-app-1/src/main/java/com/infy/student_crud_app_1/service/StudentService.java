package com.infy.student_crud_app_1.service;

import com.infy.student_crud_app_1.dto.StudentDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface StudentService {

    public String addStudent(StudentDTO studentDTO) throws StudentException;
    public StudentDTO getStudentByName(String name) throws StudentException;
    public StudentDTO getStudentById(Integer id) throws StudentException;
    public List<StudentDTO> getAll();
    public String deleteStudent(Integer id) throws StudentException;
    public StudentDTO updateStudent(Integer id,StudentDTO studentDTO);

}
