package com.infy.student_crud_app_1;

import com.infy.student_crud_app_1.dto.StudentDTO;
import com.infy.student_crud_app_1.exception.StudentException;
import com.infy.student_crud_app_1.model.Student;
import com.infy.student_crud_app_1.repo.StudentRepo;
import com.infy.student_crud_app_1.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentCrudApp1ApplicationTests {
	@Mock
	private StudentRepo studentRepo;
	@InjectMocks
	private StudentServiceImpl studentService;
	private  StudentDTO studentDTO1;
	private Student student;
	@BeforeEach
	void contextLoads() {
		studentDTO1=new StudentDTO(101,"Raja","PBS","MPC");
		student=new Student(101,"Raja","PBS","MPC");
	}

	@Test
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
}
