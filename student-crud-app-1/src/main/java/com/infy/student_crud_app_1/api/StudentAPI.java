package com.infy.student_crud_app_1.api;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.exception.StudentException;
import com.infy.student_crud_app_1.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//ResponseBody+Controller->It will make class as controller that handles http requests
@RequestMapping("/api/v1")//It will map web requests onto methods in request-handling classes
@Validated//It will validate the request
public class StudentAPI {

    @Autowired//It will inject the object dependency implicitly
    StudentService studentService;
@PostMapping(value = "/add")//It will map uri to post request
//@RequestBody ->It will convert json data to java object
// @Valid ->It will validate the request
//ResponseEntity ->It will return response to the client
    public ResponseEntity<String> addStudent(@RequestBody @Valid StudentDTO studentDTO) throws StudentException{
    String s = studentService.addStudent(studentDTO);
    return new ResponseEntity<>(s, HttpStatus.CREATED);
}
@GetMapping("/{name}")//It will map uri to get request
//@PathVariable ->It will extract the data from uri
//@RequestParam ->It will extract the data from query parameter
//@PathParam ->It will extract the data from path parameter
//Difference between @PathParam and @PathVariable is that @PathParam is used in JAX-RS and @PathVariable is used in Spring
//JAX-RS means Java API for RESTful Web Services
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
    public ResponseEntity<List<StudentDTO>> getAll() throws StudentException {
   List<StudentDTO> s= studentService.getAll();
    return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    //It will map uri to delete request
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) throws StudentException{
        String s = studentService.deleteStudent(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @PatchMapping("/{id}/update")
    //It will map uri to patch request it will update small amount of data where as PUT Mapping update bulk data
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id,@RequestBody @Valid StudentDTO studentDTO){
        StudentDTO s=studentService.updateStudent(id,studentDTO);
    return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @GetMapping("/firstFive")
    //localhost:8080/api/v1/firstFive?pageNo=0&pageSize=5
    public List<StudentDTO> getonlyFirstFive(@RequestParam("pageNo") Integer pageNo,
                                             @RequestParam("pageSize") Integer pageSize) throws StudentException{

    return studentService.getonlyFirstFive(pageNo,pageSize);
    }

    @GetMapping("/sort")
    //localhost:8080/api/v1/sort?order=name
    public List<StudentDTO> sortStudents(@RequestParam("order") String order, Sort sort) throws StudentException{

        Sort s=Sort.by(order);
     return   studentService.sortStudents(sort);


    }
}
