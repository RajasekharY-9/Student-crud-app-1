package com.infy.student_crud_app_1.service;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.exception.StudentException;
import org.hibernate.query.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentService {

    public String addStudent(StudentDTO studentDTO) throws StudentException;
    public StudentDTO getStudentByName(String name) throws StudentException;
    public StudentDTO getStudentById(Integer id) throws StudentException;
    public List<StudentDTO> getAll() throws StudentException;
    public String deleteStudent(Integer id) throws StudentException;
    public StudentDTO updateStudent(Integer id,StudentDTO studentDTO);

    public List<StudentDTO> getonlyFirstFive(Integer pageNo,Integer pageSize) throws StudentException;
    //we have to give pageNo -0 and PageSize enni pagelu kavali
    public List<StudentDTO> sortStudents(Sort sort) throws StudentException;
}
