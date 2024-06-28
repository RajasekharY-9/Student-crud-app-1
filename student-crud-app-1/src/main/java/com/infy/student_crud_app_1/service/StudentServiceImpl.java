package com.infy.student_crud_app_1.service;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.model.Student;
import com.infy.student_crud_app_1.repo.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepo studentRepo;

    @Override
    public String addStudent(StudentDTO studentDTO) throws StudentException {
        Student s=studentRepo.findByName(studentDTO.getName());
        if(s!=null){
            throw new StudentException("Student already exists");
        }else{
            Student student= studentRepo.save( convertToEntity(studentDTO));
            return "Student added with ID : "+student.getStudentId();
        }
    }

    @Override
    public StudentDTO getStudentByName(String name) throws StudentException {
        Student s=studentRepo.findByName(name);
        if(s==null){
            throw new StudentException("Student not exists");
        }
        else{
           StudentDTO studentDTO= convertToDTO(s);
            return studentDTO;
        }

    }

    @Override
    public StudentDTO getStudentById(Integer id) throws StudentException {
        Optional<Student> s=studentRepo.findById(id);
        if(s.isEmpty()){
            throw new StudentException("Student not exists");
        }
        else{
            StudentDTO studentDTO= convertToDTO(s.get());
            return studentDTO;
        }
    }

    @Override
    public List<StudentDTO> getAll() {
       List<Student> list= studentRepo.findAll();
       List<StudentDTO> studentDTOS=new ArrayList<>();
       for(Student s:list){
           StudentDTO studentDTO=convertToDTO(s);
           studentDTOS.add(studentDTO);
       }
        return studentDTOS;
    }

    @Override
    public String deleteStudent(Integer id) throws StudentException {
        Optional<Student> s=studentRepo.findById(id);
        if(s.isEmpty()){
            throw new StudentException("Student not exists");
        }
        else{
            studentRepo.deleteById(id);
        }
        return "Student deleted with ID : "+id;
    }

    @Override
    public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
        Student student=new Student();
        student.setStudentId(id);
        student.setBranch(studentDTO.getBranch());
        student.setName(studentDTO.getName());
        student.setClgName(studentDTO.getClgName());
        studentRepo.save(student);
        StudentDTO s=convertToDTO(student);

        return s;
    }

    private StudentDTO convertToDTO(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setBranch(student.getBranch());
        studentDTO.setName(student.getName());
        studentDTO.setClgName(student.getClgName());
        return studentDTO;
    }
    private Student convertToEntity(StudentDTO studentDTO){
        Student student=new Student();
       // student.setStudentId(studentDTO.getStudentId());
        student.setBranch(studentDTO.getBranch());
        student.setName(studentDTO.getName());
        student.setClgName(studentDTO.getClgName());
        return student;
    }
}
