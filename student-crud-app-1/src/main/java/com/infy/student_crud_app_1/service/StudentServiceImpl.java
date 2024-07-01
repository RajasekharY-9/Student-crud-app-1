package com.infy.student_crud_app_1.service;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.exception.StudentException;
import com.infy.student_crud_app_1.model.Student;
import com.infy.student_crud_app_1.repo.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service//indicates that an annotated class is a "Service"
@Transactional//annotation provides the application to maintain the integrity of the data means that the transaction should be executed in a single unit.

public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepo studentRepo;

    @Override
    public String addStudent(StudentDTO studentDTO) throws StudentException {
        Student s=studentRepo.findByName(studentDTO.getName());
        if(s!=null){
            throw new StudentException("Student_already_exists");
        }else{
            Student student= studentRepo.save( convertToEntity(studentDTO));
            return "Student added with ID : "+student.getStudentId();
        }
    }

    @Override
    public StudentDTO getStudentByName(String name) throws StudentException {
        Student s=studentRepo.findByName(name);
        if(s==null){
            throw new StudentException("Student_not_exists");
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
            throw new StudentException("Student_not_exists");
        }
        else{
            StudentDTO studentDTO= convertToDTO(s.get());
            return studentDTO;
        }
    }

    @Override
    public List<StudentDTO> getAll() throws StudentException {
       List<Student> list= studentRepo.findAll();
       if(list.isEmpty()){
           throw new StudentException("No_Students_Found");
       }
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
            throw new StudentException("Student_not_exists");
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

        StudentDTO s=convertToDTO( studentRepo.save(student));

        return s;
    }

    @Override
    public List<StudentDTO> getonlyFirstFive(Integer pageNo, Integer pageSize) throws StudentException {
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        Page<Student> page=studentRepo.findAll(pageable);
        if(page.isEmpty()){
            throw new StudentException("Student_not_exists");
        }
        else{
            return   page.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
        }

    }

    @Override
    public List<StudentDTO> sortStudents(Sort sort) throws StudentException {
        Iterable<Student> list = studentRepo.findAll(sort);
if(list==null){
    throw new StudentException("Student_not_exists");
}
List<StudentDTO> dtos=new ArrayList<>();
for(Student s:list){
    dtos.add(convertToDTO(s));
}

        return dtos;
    }

    public StudentDTO convertToDTO(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setBranch(student.getBranch());
        studentDTO.setName(student.getName());
        studentDTO.setClgName(student.getClgName());
        return studentDTO;
    }
    public Student convertToEntity(StudentDTO studentDTO){
        Student student=new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setBranch(studentDTO.getBranch());
        student.setName(studentDTO.getName());
        student.setClgName(studentDTO.getClgName());
        return student;
    }
}
