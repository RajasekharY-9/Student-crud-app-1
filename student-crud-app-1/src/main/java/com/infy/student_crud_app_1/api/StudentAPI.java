package com.infy.student_crud_app_1.api;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.model.Student;
import com.infy.student_crud_app_1.service.StudentException;
import com.infy.student_crud_app_1.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class StudentAPI {

    @Autowired
    StudentService studentService;
@PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody @Valid StudentDTO studentDTO) throws StudentException{
    String s = studentService.addStudent(studentDTO);
    return new ResponseEntity<>(s, HttpStatus.CREATED);
}
@GetMapping("/{name}")
    public ResponseEntity<StudentDTO> getStudentByName(@PathVariable String name) throws StudentException{
    StudentDTO student = studentService.getStudentByName(name);
    return new ResponseEntity<>(student,HttpStatus.OK);
}
@GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id) throws StudentException{
    StudentDTO student = studentService.getStudentById(id);
    return new ResponseEntity<>(student,HttpStatus.OK);
}
@GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAll(){
   List<StudentDTO> s= studentService.getAll();
    return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) throws StudentException{
        String s = studentService.deleteStudent(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @PatchMapping("/{id}/update")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id,@RequestBody @Valid StudentDTO studentDTO){
        StudentDTO s=studentService.updateStudent(id,studentDTO);
    return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
