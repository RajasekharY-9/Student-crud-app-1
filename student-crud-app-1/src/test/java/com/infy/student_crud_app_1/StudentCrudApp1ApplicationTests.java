
package com.infy.student_crud_app_1;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.exception.StudentException;
import com.infy.student_crud_app_1.model.Student;
import com.infy.student_crud_app_1.repo.StudentRepo;
import com.infy.student_crud_app_1.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentCrudApp1ApplicationTests {
	@Mock//Mockito mock object means it will create a dummy object of the class
	private StudentRepo studentRepo;
	@InjectMocks//InjectMocks annotation is used to create and inject the mock object
	private StudentServiceImpl studentService;
	private  StudentDTO studentDTO1;
	private Student student;

	@BeforeEach
	void contextLoads() {
		studentDTO1=new StudentDTO(101,"Raja","PBS","MPC");
		student=new Student(101,"Raja","PBS","MPC");
	}

	@Test//The @Test annotation tells JUnit that the public void method to which it is attached can be run as a test case
	public void testAddStudentSuccess() throws StudentException {
		// Mock studentRepo.findByName to return null (student doesn't exist)
		when(studentRepo.findByName(studentDTO1.getName())).thenReturn(null);

		// Mock studentRepo.save to return a student with ID 101 (assuming convertToEntity is implemented)
		when(studentRepo.save(any(Student.class))).thenReturn(student);

		// Call the service method and assert the success message
		String response = studentService.addStudent(studentDTO1);

		assertEquals("Student added with ID : 101", response);
	}
	@Test
	void testAddStudentFailure() {
		// Mock studentRepo.findByName to return an existing student
		when(studentRepo.findByName(studentDTO1.getName())).thenReturn(student);

		// Call the service method and expect an exception
		StudentException exception = assertThrows(StudentException.class, () -> {
			studentService.addStudent(studentDTO1);
		});

		assertEquals("Student_already_exists", exception.getMessage());
	}
	@Test
	void testGetStudentByNameSuccess() throws StudentException {
		when(studentRepo.findByName(student.getName())).thenReturn(student);
		StudentDTO result = studentService.getStudentByName(student.getName());
		assertNotNull(result);
		assertEquals(studentDTO1.getName(), result.getName());
	}
	@Test
	void testGetStudentByNameFailure() {
		when(studentRepo.findByName(student.getName())).thenReturn(null);

		StudentException exception = assertThrows(StudentException.class, () -> {
			studentService.getStudentByName(student.getName());
		});

		assertEquals("Student_not_exists", exception.getMessage());
	}

	@Test
	void testGetStudentByIdSuccess() throws StudentException {
		when(studentRepo.findById(student.getStudentId())).thenReturn(Optional.of(student));

		StudentDTO result = studentService.getStudentById(student.getStudentId());
		assertNotNull(result);
		assertEquals(studentDTO1.getName(), result.getName());
	}

	@Test
	void testGetStudentByIdFailure() {
		when(studentRepo.findById(student.getStudentId())).thenReturn(Optional.empty());

		StudentException exception = assertThrows(StudentException.class, () -> {
			studentService.getStudentById(student.getStudentId());
		});

		assertEquals("Student_not_exists", exception.getMessage());
	}

	@Test
	void testGetAllStudents() throws StudentException {
		List<Student> students = Arrays.asList(student);
		when(studentRepo.findAll()).thenReturn(students);

		List<StudentDTO> result = studentService.getAll();
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(studentDTO1.getName(), result.get(0).getName());
		//assertEquals(studentDTO1.getName(), result.get(0).getName());
	}

	@Test
	void testGetAllStudentsEmpty() throws StudentException {
		when(studentRepo.findAll()).thenReturn(Arrays.asList());
StudentException exception=assertThrows(StudentException.class,()->{
	studentService.getAll();
});

		assertEquals("No_Students_Found",exception.getMessage());
	}

	@Test
	void testDeleteStudentSuccess() throws StudentException {
		when(studentRepo.findById(student.getStudentId())).thenReturn(Optional.of(student));
		doNothing().when(studentRepo).deleteById(student.getStudentId());

		String response = studentService.deleteStudent(student.getStudentId());
		assertEquals("Student deleted with ID : " + student.getStudentId(), response);
	}

	@Test
	void testDeleteStudentFailure() {
		when(studentRepo.findById(student.getStudentId())).thenReturn(Optional.empty());

		StudentException exception = assertThrows(StudentException.class, () -> {
			studentService.deleteStudent(student.getStudentId());
		});

		assertEquals("Student_not_exists", exception.getMessage());
	}

	@Test
	void testUpdateStudentSuccess() {
		StudentDTO updatedStudentDTO = new StudentDTO(101, "Raja", "PBS", "BiPC");
		Student updatedStudent = new Student(101, "Raja", "PBS", "BiPC");
		when(studentRepo.save(any(Student.class))).thenReturn(updatedStudent);

		StudentDTO result = studentService.updateStudent(101, updatedStudentDTO);
		assertNotNull(result);
		assertEquals(updatedStudentDTO.getBranch(), result.getBranch());
	}
}

